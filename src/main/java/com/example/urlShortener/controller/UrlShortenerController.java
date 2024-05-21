package com.example.urlShortener.controller;

import com.example.urlShortener.model.UrlMapping;
import com.example.urlShortener.service.UrlShortenerService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/url")
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @PostMapping("/shorten")
    public UrlMapping shortenUrl(@RequestBody UrlMapping urlMapping) {
        return urlShortenerService.shortenUrl(urlMapping.getOriginalUrl());
    }

    @GetMapping("/{shortUrl}")
    public void redirectUrl(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        String originalUrl = urlShortenerService.getOriginalUrl(shortUrl);
        response.sendRedirect(originalUrl);
    }

    @GetMapping("/metrics")
    public List<Map.Entry<String, Integer>> getMetrics() {
        return urlShortenerService.getTopDomains();
    }
}
