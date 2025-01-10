package com.shortenurl.demo.domain;

import java.time.Instant;
import java.util.Base64;

public class Base64ShortKeyGenerator implements ShortKeyGenerator {

    @Override
    public String generateKey(String originalUrl) {
        String url = Base64.getUrlEncoder().withoutPadding().encodeToString(originalUrl.getBytes()).substring(0, 4);
        String time = Base64.getUrlEncoder().withoutPadding().encodeToString(String.valueOf(Instant.now().toEpochMilli()).getBytes());

        return url.concat(time.substring(time.length() - 3, time.length()));
    }
}

