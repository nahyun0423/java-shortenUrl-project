package com.shortenurl.demo;

import com.shortenurl.demo.application.ShortenUrlService;
import com.shortenurl.demo.presentation.ShortenUrlController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShortenUrlController.class)
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ShortenUrlService shortenUrlService;

    @Test
    void testHandleIllegalArgumentException() throws Exception {
        when(shortenUrlService.getOriginalUrl("testKey"))
                .thenThrow(new IllegalArgumentException("testKey"));

        mockMvc.perform(get("/testKey"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("입력값을 찾을 수 없습니다. 입력값 : testKey"));
    }
}
