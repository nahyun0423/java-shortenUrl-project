package com.shortenurl.demo;

import com.shortenurl.demo.application.ShortenUrlService;
import com.shortenurl.demo.domain.Base64ShortKeyGenerator;
import com.shortenurl.demo.domain.ShortKeyGenerator;
import com.shortenurl.demo.domain.ShortenUrl;
import com.shortenurl.demo.infrastructure.ShortenUrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ShortenUrlServiceTest {
    private final ShortKeyGenerator shortKeyGenerator = new Base64ShortKeyGenerator();
    private ShortenUrlRepository shortenUrlRepository;
    private ShortenUrlService shortenUrlService;

    @BeforeEach
    void setUp() {
        shortenUrlRepository = Mockito.mock(ShortenUrlRepository.class);
        shortenUrlService = new ShortenUrlService(shortenUrlRepository);
    }


    @Test
    void testSaveShortenUrl() {
        ShortenUrl shortenUrl = new ShortenUrl("https://example.com", "shortKey123");
        when(shortenUrlRepository.save(shortenUrl)).thenReturn(shortenUrl);

        shortenUrlService.saveShortenUrl(shortenUrl);

        when(shortenUrlRepository.findByShortenUrl("shortKey123")).thenReturn(shortenUrl);

        ShortenUrl result = shortenUrlRepository.findByShortenUrl("shortKey123");
        assertNotNull(result);
        assertEquals("https://example.com", result.getOriginalUrl());
    }

    @Test
    void testCreateShortenUrl() {
        String originalUrl = "https://example.com";
        String result = shortenUrlService.createShortenUrl(originalUrl);

        assertNotNull(result);
        assertTrue(result.length() > 0);
    }

    @Test
    void testRedirectOriginalUrl_Success() {
        ShortenUrl shortenUrl = new ShortenUrl("https://example.com", "shortKey123");
        when(shortenUrlRepository.findByShortenUrl("shortKey123")).thenReturn(shortenUrl);

        String result = shortenUrlService.redirectOriginalUrl("shortKey123");

        assertEquals("https://example.com", result);
        assertEquals(1, shortenUrl.getRedirectCount());
    }

    @Test
    void testRedirectOriginalUrl_NotFound() {
        String shortKey = "noKey";
        when(shortenUrlRepository.findByShortenUrl(shortKey)).thenReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            shortenUrlService.redirectOriginalUrl(shortKey);
        });

        assertEquals(shortKey, exception.getMessage());
    }
}
