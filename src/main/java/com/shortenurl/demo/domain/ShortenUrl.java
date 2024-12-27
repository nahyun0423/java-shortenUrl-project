package com.shortenurl.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
public class ShortenUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalUrl;
    private String shortenUrl;
    private int redirectCount;

    public ShortenUrl(String originalUrl, String newShortenUrl) {
        this.originalUrl = originalUrl;
        this.shortenUrl = newShortenUrl;
        this.redirectCount = 0;
    }
}
