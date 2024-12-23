package com.shortenurl.demo.presentation;

import com.shortenurl.demo.application.ShortenUrlService;
import com.shortenurl.demo.domain.ShortenUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShortenUrlController {
    private final ShortenUrlService shortenUrlService;
    private final ShortenUrlDto shortenUrlDto;

    @GetMapping(value = "/shortenUrl")
    public ShortenUrlDto createShortenUrl(@RequestBody String originalUrl) {
        String newShortenUrl = shortenUrlService.createShortenUrl();
        ShortenUrl shortenUrl = new ShortenUrl(originalUrl, newShortenUrl);
        shortenUrlService.saveShortenUrl(shortenUrl);
        return shortenUrlDto.toDto(shortenUrl);
    }

}
