package com.shortenurl.demo.application;

import java.util.Random;

public class CreateShortenUrlService {
    private Random random = new Random();
    private String randomWord = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ23456789"; //헷갈릴 수 있는 0,o,l,1은 제외

    public String createShortenUrl() {
        String resultUrl = "";

        for (int i = 0; i < 5; i++) {
            char a = randomWord.charAt(random.nextInt(randomWord.length()));
            resultUrl.concat(String.valueOf(a));
        }
        return resultUrl;
    }
}
