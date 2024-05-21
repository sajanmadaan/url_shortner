package com.example.urlShortener;

import com.example.urlShortener.model.UrlMapping;
import com.example.urlShortener.repository.UrlShortenerRepository;
import com.example.urlShortener.service.UrlShortenerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UrlShortenerApplicationTests {

    @Autowired
    private UrlShortenerRepository urlShortenerRepository;

    @Autowired
    private UrlShortenerService urlShortenerService;

    @Test
    void contextLoads() {
    }

    @Test
    void testShortenUrl() {
        String originalUrl = "https://www.example.com";
        UrlMapping urlMapping = urlShortenerService.shortenUrl(originalUrl);
        assertNotNull(urlMapping);
        assertEquals(originalUrl, urlMapping.getOriginalUrl());
    }

    @Test
    void testRedirectUrl() {
        String originalUrl = "https://www.example.com";
        UrlMapping urlMapping = urlShortenerService.shortenUrl(originalUrl);
        String retrievedUrl = urlShortenerService.getOriginalUrl(urlMapping.getShortUrl());
        assertEquals(originalUrl, retrievedUrl);
    }
}
