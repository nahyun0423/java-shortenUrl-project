package com.shortenurl.demo;

import com.shortenurl.demo.application.CreateShortenUrlService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateShortenUrlTest {
    CreateShortenUrlService createShortenUrlService = new CreateShortenUrlService();

    @Test
    void 단축url_길이_테스트() {
        String testUrl = createShortenUrlService.createShortenUrl();
        Assertions.assertEquals(5, testUrl.length());
    }

    @Test
    void 단축url_랜덤_생성_테스트() {
        String testUrl1 = createShortenUrlService.createShortenUrl();
        String testUrl2 = createShortenUrlService.createShortenUrl();
        Assertions.assertFalse(testUrl1.equals(testUrl2));
    }
}
