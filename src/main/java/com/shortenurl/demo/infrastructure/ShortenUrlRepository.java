package com.shortenurl.demo.infrastructure;

import com.shortenurl.demo.domain.ShortenUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortenUrlRepository extends JpaRepository<ShortenUrl, Long> {
}
