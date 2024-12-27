package com.shortenurl.demo;

import com.shortenurl.demo.domain.Base64ShortKeyGenerator;
import com.shortenurl.demo.domain.ShortKeyGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Base64ShortKeyGeneratorTest {
    private ShortKeyGenerator shortKeyGenerator = new Base64ShortKeyGenerator();

    @Test
    void 단축url_길이_테스트() {
        String url = "https://www.google.co.kr/";
        String testUrl = shortKeyGenerator.generateKey(url);
        Assertions.assertEquals(7, testUrl.length());
    }

    @Test
    void 단축url_랜덤_생성_테스트() throws InterruptedException {
        String url = "https://www.google.co.kr/";
        String testUrl1 = shortKeyGenerator.generateKey(url);
        Thread.sleep(10);
        String testUrl2 = shortKeyGenerator.generateKey(url);
        Assertions.assertFalse(testUrl1.equals(testUrl2));
    }
}
