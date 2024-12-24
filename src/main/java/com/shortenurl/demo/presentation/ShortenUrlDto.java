package com.shortenurl.demo.presentation;

import com.shortenurl.demo.domain.ShortenUrl;

public class ShortenUrlDto {
    private String originalUrl;
    private String shortenUrl;
    private int redirectCount;

    public ShortenUrlDto(String originalUrl, String shortenUrl, int redirectCount) {
        this.originalUrl = originalUrl;
        this.shortenUrl = shortenUrl;
        this.redirectCount = redirectCount;
    }

    public static ShortenUrlDto toDto(ShortenUrl shortenUrl) {
        return new ShortenUrlDto(
                shortenUrl.getOriginalUrl(), shortenUrl.getShortenUrl(), shortenUrl.getRedirectCount());
    }
}
