package com.shortenurl.demo;

import com.shortenurl.demo.application.ShortenUrlService;
import com.shortenurl.demo.infrastructure.ShortenUrlRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateShortenUrlTest {
    private ShortenUrlService shortenUrlService = new ShortenUrlService(ShortenUrlRepository);

    @Test
    void 단축url_길이_테스트() {
        String url = "https://www.google.co.kr/";
        String testUrl = shortenUrlService.createShortenUrl(url);
        Assertions.assertEquals(7, testUrl.length());
    }

    @Test
    void 단축url_랜덤_생성_테스트() {
        String url = "https://www.google.co.kr/";
        String testUrl1 = shortenUrlService.createShortenUrl(url);
        String testUrl2 = shortenUrlService.createShortenUrl(url);
        Assertions.assertFalse(testUrl1.equals(testUrl2));
    }
}
