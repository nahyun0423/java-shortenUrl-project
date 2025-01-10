package com.shortenurl.demo;

import com.shortenurl.demo.domain.ShortenUrl;
import com.shortenurl.demo.infrastructure.ShortenUrlRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ShortenUrlRepositoryTest {

    @Autowired
    private ShortenUrlRepository shortenUrlRepository;

    @Test
    void testSaveAndFindByShortenUrl() {
        ShortenUrl shortenUrl = new ShortenUrl("https://example.com", "shortKey123");

        shortenUrlRepository.save(shortenUrl);
        ShortenUrl foundUrl = shortenUrlRepository.findByShortenUrl("shortKey123");

        assertThat(foundUrl).isNotNull();
        assertThat(foundUrl.getOriginalUrl()).isEqualTo("https://example.com");
        assertThat(foundUrl.getShortenUrl()).isEqualTo("shortKey123");
    }

    @Test
    void testFindByShortenUrl_NotFound() {
        ShortenUrl foundUrl = shortenUrlRepository.findByShortenUrl("nonExistentKey");
        assertThat(foundUrl).isNull();
    }
}