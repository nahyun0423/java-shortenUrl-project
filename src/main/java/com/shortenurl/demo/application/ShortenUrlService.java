package com.shortenurl.demo.application;

import com.shortenurl.demo.domain.Base64ShortKeyGenerator;
import com.shortenurl.demo.domain.ShortKeyGenerator;
import com.shortenurl.demo.domain.ShortenUrl;
import com.shortenurl.demo.infrastructure.ShortenUrlRepository;
import com.shortenurl.demo.presentation.ShortenUrlDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public String redirectOriginalUrl(String shortKey) {
        ShortenUrl shortenUrl = shortenUrlRepository.findByShortenUrl(shortKey);

        if (shortenUrl == null) {
            throw new IllegalArgumentException(shortKey);
        }
        shortenUrl.incrementRedirectCount();

        return shortenUrl.getOriginalUrl();
    }

    public ShortenUrlDto getShortenUrl(String shortKey) {
        ShortenUrl shortenUrl = shortenUrlRepository.findByShortenUrl(shortKey);

        if (shortenUrl == null) {
            throw new IllegalArgumentException();
        }

        return new ShortenUrlDto(shortenUrl);
    }

    public List<ShortenUrlDto> getAllShortenUrls() {
        List<ShortenUrl> shortenUrls = shortenUrlRepository.findAll();
        return shortenUrls.stream()
                .map(ShortenUrlDto::new)
                .collect(Collectors.toList());
    }
}