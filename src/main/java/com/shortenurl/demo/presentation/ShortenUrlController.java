package com.shortenurl.demo.presentation;

import com.shortenurl.demo.application.ShortenUrlService;
import com.shortenurl.demo.domain.ShortenUrl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ShortenUrlController {
    private final ShortenUrlService shortenUrlService;

    @PostMapping(value = "/shortenUrl")
    public ShortenUrlDto createShortenUrl(@RequestBody String originalUrl) {
        String newShortenUrl = shortenUrlService.createShortenUrl(originalUrl);
        ShortenUrl shortenUrl = new ShortenUrl(originalUrl, newShortenUrl);
        shortenUrlService.saveShortenUrl(shortenUrl);
        return new ShortenUrlDto(shortenUrl);
    }

    @GetMapping(value = "/redirect/{shortenUrl}")
    public void redirectOriginalUrl(@PathVariable String shortenUrl, HttpServletResponse response) throws IOException {
        String originalUrl = shortenUrlService.getOriginalUrl(shortenUrl);
        response.sendRedirect(originalUrl);
    }


}
