package com.shortenurl.demo.domain;

import lombok.Getter;

@Getter
public class ShortenUrl {
    private String originalUrl;
    private String shortenUrl;
    private int redirectCount;

    public ShortenUrl(String originalUrl, String newShortenUrl) {
        this.originalUrl = originalUrl;
        this.shortenUrl = newShortenUrl;
        this.redirectCount = 0;
    }
}
