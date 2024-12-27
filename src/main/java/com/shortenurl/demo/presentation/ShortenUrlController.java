package com.shortenurl.demo.presentation;

import com.shortenurl.demo.application.ShortenUrlService;
import com.shortenurl.demo.domain.RandomShortKeyGenerator;
import com.shortenurl.demo.domain.ShortKeyGenerator;
import com.shortenurl.demo.domain.ShortenUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShortenUrlController {
    private final ShortenUrlService shortenUrlService;

    @GetMapping(value = "/shortenUrl")
    public ShortenUrlDto createShortenUrl(@RequestBody String originalUrl) {
        String newShortenUrl = shortenUrlService.createShortenUrl(originalUrl);
        ShortenUrl shortenUrl = new ShortenUrl(originalUrl, newShortenUrl);
        shortenUrlService.saveShortenUrl(shortenUrl);
        return new ShortenUrlDto(shortenUrl);
    }

}
