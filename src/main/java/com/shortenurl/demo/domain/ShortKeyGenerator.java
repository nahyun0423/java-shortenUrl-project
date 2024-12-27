package com.shortenurl.demo.domain;

import java.util.Objects;

public interface ShortKeyGenerator {
    String generateKey(Object encoded);
}
