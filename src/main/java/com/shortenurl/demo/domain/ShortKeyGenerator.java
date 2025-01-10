package com.shortenurl.demo.domain;

public interface ShortKeyGenerator {
    String generateKey(String originalUrl);
}
