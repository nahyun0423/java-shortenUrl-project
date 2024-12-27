package com.shortenurl.demo.application;

import com.shortenurl.demo.domain.Base64ShortKeyGenerator;
import com.shortenurl.demo.domain.RandomShortKeyGenerator;
import com.shortenurl.demo.domain.ShortKeyGenerator;
import com.shortenurl.demo.domain.ShortenUrl;
import com.shortenurl.demo.infrastructure.ShortenUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShortenUrlService {
    private final ShortenUrlRepository shortenUrlRepository;
    private ShortKeyGenerator shortKeyGenerator;

    public void saveShortenUrl(ShortenUrl shortenUrl) {
        shortenUrlRepository.save(shortenUrl);
    }

    public String createShortenUrl(String originalUrl) {
        shortKeyGenerator = new Base64ShortKeyGenerator();
        return shortKeyGenerator.generateKey(originalUrl);
    }
}
