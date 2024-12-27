package com.shortenurl.demo.presentation;

import com.shortenurl.demo.domain.ShortenUrl;

public class ShortenUrlDto {
    private String originalUrl;
    private String shortenUrl;
    private int redirectCount;

    public ShortenUrlDto(ShortenUrl shortenUrl) {
        this.originalUrl = shortenUrl.getOriginalUrl();
        this.shortenUrl = shortenUrl.getShortenUrl();
        this.redirectCount = shortenUrl.getRedirectCount();
    }
}
