package com.shortenurl.demo;

import com.shortenurl.demo.application.ShortenUrlService;
import com.shortenurl.demo.domain.Base64ShortKeyGenerator;
import com.shortenurl.demo.domain.ShortKeyGenerator;
import com.shortenurl.demo.domain.ShortenUrl;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ShortenUrlServiceTest {
    private final ShortKeyGenerator shortKeyGenerator = new Base64ShortKeyGenerator();
    private com.shortenurl.demo.infrastructure.ShortenUrlRepository ShortenUrlRepository;
    private final ShortenUrlService shortenUrlService = new ShortenUrlService( ShortenUrlRepository);

    @Test
    void testSaveShortenUrl() {
        ShortenUrl shortenUrl = new ShortenUrl("https://example.com", "shortKey123");
        shortenUrlService.saveShortenUrl(shortenUrl);

        ShortenUrl result = MapShortenUrlRepository.findByShortenUrl("shortKey123");
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
        MapShortenUrlRepository.save(shortenUrl);

        String result = shortenUrlService.redirectOriginalUrl("shortKey123");

        assertEquals("https://example.com", result);
        assertEquals(1, shortenUrl.getRedirectCount());
    }

    @Test
    void testRedirectOriginalUrl_NotFound() {
        String shortKey = "noKey";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            shortenUrlService.redirectOriginalUrl(shortKey);
        });

        assertEquals(shortKey, exception.getMessage());
    }

    private static abstract class MapShortenUrlRepository {
        private static Map<String, ShortenUrl> storage = new HashMap<>();

        public static ShortenUrl findByShortenUrl(String shortKey) {
            return storage.get(shortKey);
        }

        public static void save(ShortenUrl shortenUrl) {
            storage.put(shortenUrl.getShortenUrl(), shortenUrl);
        }
    }
}
