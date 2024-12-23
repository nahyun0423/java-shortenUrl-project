package com.shortenurl.demo.application;

import com.shortenurl.demo.domain.ShortenUrl;
import com.shortenurl.demo.infrastructure.ShortenUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class ShortenUrlService {
    private final ShortenUrlRepository shortenUrlRepository;
    private Random random = new Random();
    private String randomWord = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ23456789"; //헷갈릴 수 있는 0,o,l,1은 제외

    public String createShortenUrl() {
        String resultUrl = "";

        for (int i = 0; i < 5; i++) {
            char a = randomWord.charAt(random.nextInt(randomWord.length()));
            resultUrl += String.valueOf(a);
        }
        return resultUrl;
    }

    public void saveShortenUrl(ShortenUrl shortenUrl) {
        shortenUrlRepository.save(shortenUrl);
    }
}
