package com.shortenurl.demo;

import com.shortenurl.demo.domain.Base64ShortKeyGenerator;
import com.shortenurl.demo.domain.ShortKeyGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Base64ShortKeyGeneratorTest {
    private ShortKeyGenerator shortKeyGenerator = new Base64ShortKeyGenerator();

    @Test
    void testShortenUrlLength_7() {
        String url = "https://www.google.co.kr/";
        String testUrl = shortKeyGenerator.generateKey(url);
        Assertions.assertEquals(7, testUrl.length());
    }

    @Test
    void testCreateShortenUrl() throws InterruptedException {
        String url = "https://www.google.co.kr/";
        String testUrl1 = shortKeyGenerator.generateKey(url);
        Thread.sleep(10);
        String testUrl2 = shortKeyGenerator.generateKey(url);
        Assertions.assertFalse(testUrl1.equals(testUrl2));
    }
}
