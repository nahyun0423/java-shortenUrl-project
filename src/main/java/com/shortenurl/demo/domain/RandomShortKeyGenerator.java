package com.shortenurl.demo.domain;

import java.security.SecureRandom;
import java.util.Base64;

public class RandomShortKeyGenerator implements ShortKeyGenerator {
    private SecureRandom secureRandom = new SecureRandom();

    @Override
    public String generateKey(Object encoded) {
        byte[] randomBytes = new byte[5];
        secureRandom.nextBytes(randomBytes);

        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes).substring(0,7);
    }
}
