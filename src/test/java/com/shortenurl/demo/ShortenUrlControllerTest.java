package com.shortenurl.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shortenurl.demo.application.ShortenUrlService;
import com.shortenurl.demo.domain.ShortenUrl;
import com.shortenurl.demo.presentation.ShortenUrlController;
import com.shortenurl.demo.presentation.ShortenUrlDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
public class ShortenUrlControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ShortenUrlService shortenUrlService;
    private ShortenUrlDto shortenUrlDto;

    @InjectMocks
    private ShortenUrlController shortenUrlController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(shortenUrlController).build();
    }

    @Test
    void testCreateShortenUrl() throws Exception {
        ShortenUrlDto shortenUrlDto = new ShortenUrlDto(new ShortenUrl("https://example.com", "shortKey123"));

        when(shortenUrlService.createShortenUrl("https://example.com")).thenReturn("shortKey123");

        mockMvc.perform(MockMvcRequestBuilders.post("/shortenUrl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(Map.of("originalUrl", "https://example.com"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.originalUrl").value("https://example.com"))
                .andExpect(jsonPath("$.shortenUrl").value("shortKey123"));

    }

    @Test
    void testRedirectOriginalUrl() throws Exception {
        when(shortenUrlService.redirectOriginalUrl("shortKey123")).thenReturn("https://example.com");

        mockMvc.perform(get("/shortKey123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("https://example.com"));
    }

    @Test
    void testGetShortenUrl() throws Exception {
        when(shortenUrlService.getShortenUrl("shortKey123")).thenReturn(shortenUrlDto);

        mockMvc.perform(get("/get/shortKey123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.originalUrl").value("https://example.com"))
                .andExpect(jsonPath("$.shortenUrl").value("shortKey123"));
    }

    @Test
    void testGetAllShortenUrls() throws Exception {
        when(shortenUrlService.getAllShortenUrls()).thenReturn(List.of(shortenUrlDto));

        mockMvc.perform(get("/get/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].originalUrl").value("https://example.com"))
                .andExpect(jsonPath("$[0].shortenUrl").value("shortKey123"));
    }
}