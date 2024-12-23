package com.shortenurl.demo.presentation;

import com.shortenurl.demo.domain.ShortenUrl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortenUrlController {

    @GetMapping(value = "/shortenUrl")
    public ShortenUrlDto createShortenUrl(@RequestBody String originalUrl) {
        ShortenUrl shortenUrl = new ShortenUrl(originalUrl);
        return ShortenUrlDto.toDto(shortenUrl);
    }

}
