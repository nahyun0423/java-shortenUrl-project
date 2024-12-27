package com.shortenurl.demo.domain;

import java.util.Base64;

public class Base64ShortKeyGenerator implements ShortKeyGenerator{
    @Override
    public String generateKey(Object encoded) {
        String originalUrl = (String) encoded;
        return Base64.getUrlEncoder().withoutPadding().encodeToString(originalUrl.getBytes());
    }
}
