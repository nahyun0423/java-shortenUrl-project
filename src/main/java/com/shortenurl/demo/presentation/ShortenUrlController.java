package com.shortenurl.demo.presentation;

import com.shortenurl.demo.application.ShortenUrlService;
import com.shortenurl.demo.domain.ShortenUrl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ShortenUrlController {
    private final ShortenUrlService shortenUrlService;

    @PostMapping(value = "/shortenUrl")
    public ResponseEntity<ShortenUrlDto> createShortenUrl(@RequestBody Map<String, String> requestBody) {
        String originalUrl = requestBody.get("originalUrl");
        String newShortenUrl = shortenUrlService.createShortenUrl(originalUrl);
        ShortenUrl shortenUrl = new ShortenUrl(originalUrl, newShortenUrl);
        shortenUrlService.saveShortenUrl(shortenUrl);
        return ResponseEntity.ok(new ShortenUrlDto(shortenUrl));
    }

    @GetMapping(value = "/redirect/{shortenUrl}")
    public void redirectOriginalUrl(@PathVariable String shortenUrl, HttpServletResponse response) throws IOException {
        String originalUrl = shortenUrlService.redirectOriginalUrl(shortenUrl);
        response.sendRedirect(originalUrl);
    }
}
