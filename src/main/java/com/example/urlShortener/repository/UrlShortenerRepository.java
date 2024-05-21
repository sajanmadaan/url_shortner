package com.example.urlShortener.repository;

import com.example.urlShortener.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlShortenerRepository extends JpaRepository<UrlMapping, Long> {
    Optional<UrlMapping> findByOriginalUrl(String originalUrl);
    Optional<UrlMapping> findByShortUrl(String shortUrl);
}
