package com.shortenurl.demo;

import com.shortenurl.demo.presentation.ShortenUrlDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void testCreateAndRedirectShortenUrl() {
		// create
		Map<String, String> requestBody = Map.of("originalUrl", "https://example.com");
		ResponseEntity<ShortenUrlDto> createResponse = restTemplate.postForEntity("/shortenUrl", requestBody, ShortenUrlDto.class);

		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		ShortenUrlDto createdShortenUrl = createResponse.getBody();
		assertThat(createdShortenUrl).isNotNull();
		assertThat(createdShortenUrl.getOriginalUrl()).isEqualTo("https://example.com");

		// redirect
		ResponseEntity<Void> redirectResponse = restTemplate.getForEntity("/" + createdShortenUrl.getShortenUrl(), Void.class);
		assertThat(redirectResponse.getStatusCode()).isEqualTo(HttpStatus.FOUND);
	}

	@Test
	void testGetShortenUrl() {
		// create
		Map<String, String> requestBody = Map.of("originalUrl", "https://example.com");
		ShortenUrlDto createdShortenUrl = restTemplate.postForObject("/shortenUrl", requestBody, ShortenUrlDto.class);

		// 조회
		ResponseEntity<ShortenUrlDto> getResponse = restTemplate.getForEntity("/get/" + createdShortenUrl.getShortenUrl(), ShortenUrlDto.class);
		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

		ShortenUrlDto shortenUrlDto = getResponse.getBody();
		assertThat(shortenUrlDto).isNotNull();
		assertThat(shortenUrlDto.getOriginalUrl()).isEqualTo("https://example.com");
		assertThat(shortenUrlDto.getShortenUrl()).isEqualTo(createdShortenUrl.getShortenUrl());
	}
}
