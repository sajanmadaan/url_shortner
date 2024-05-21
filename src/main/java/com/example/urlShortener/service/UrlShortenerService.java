package com.example.urlShortener.service;

import com.example.urlShortener.repository.UrlShortenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.urlShortener.model.UrlMapping;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class UrlShortenerService {

    @Autowired
    private UrlShortenerRepository urlShortenerRepository;

    private static final String BASE_URL = "https://localhost:8080/url/";

    public UrlMapping shortenUrl(String originalUrl) {
        return urlShortenerRepository.findByOriginalUrl(originalUrl)
                .orElseGet(() -> {
                    String shortUrl = generateShortUrl(originalUrl);
                    shortUrl = BASE_URL + shortUrl;
                    UrlMapping urlMapping = new UrlMapping(null, originalUrl, shortUrl);
                    return urlShortenerRepository.save(urlMapping);
                });
    }

    public String getOriginalUrl(String shortUrl) {
        shortUrl = BASE_URL + shortUrl;
        return urlShortenerRepository.findByShortUrl(shortUrl)
                .map(UrlMapping::getOriginalUrl)
                .orElseThrow(() -> new RuntimeException("URL not found"));
    }

    public List<Map.Entry<String, Integer>> getTopDomains() {
        List<UrlMapping> allMappings = urlShortenerRepository.findAll();
        Map<String, Integer> domainCount = new HashMap<>();

        for (UrlMapping mapping : allMappings) {
            String domain = getDomain(mapping.getOriginalUrl());
            domainCount.put(domain, domainCount.getOrDefault(domain, 0) + 1);
        }

        return domainCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .collect(Collectors.toList());
    }

    private String generateShortUrl(String originalUrl) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(originalUrl.getBytes());
            return Base64.getUrlEncoder().encodeToString(digest).substring(0, 6);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating short URL", e);
        }
    }

    private String getDomain(String url) {
        try {
            URI uri = new URI(url);
            return uri.getHost();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Invalid URL", e);
        }
    }
}
