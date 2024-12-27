package com.shortenurl.demo;

import com.shortenurl.demo.application.ShortenUrlService;
import com.shortenurl.demo.infrastructure.ShortenUrlRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateShortenUrlTest {
    private ShortenUrlRepository ShortenUrlRepository;
    private ShortenUrlService shortenUrlService = new ShortenUrlService(ShortenUrlRepository);

    @Test
    void 단축url_길이_테스트() {
        String testUrl = shortenUrlService.createShortenUrl();
        Assertions.assertEquals(7, testUrl.length());
    }

    @Test
    void 단축url_랜덤_생성_테스트() {
        String testUrl1 = shortenUrlService.createShortenUrl();
        String testUrl2 = shortenUrlService.createShortenUrl();
        Assertions.assertFalse(testUrl1.equals(testUrl2));
    }
}
