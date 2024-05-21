# url_shortner
 url_shortner


## Overview

This is a URL Shortener service implemented using Spring Boot and Java. The service provides RESTful APIs to shorten URLs, retrieve the original URLs, and track the most frequently shortened domains.

## Features

- Shorten a URL and receive a shortened URL.
- Retrieve the original URL from the shortened URL.
- Track and display the top 3 most frequently shortened domains.

## Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database
- Jakarta Persistence API

## Setup Instructions

### Prerequisites

- Java 17
- Maven


API Endpoints

1. Shorten URL
   URL: /url/shorten
   Method: POST

## Request Body 

   {
   "url": "https://www.youtube.com/watch?v=a199KZGMNxk"
   }

## Response

   {
   "originalUrl": "https://www.youtube.com/watch?v=a199KZGMNxk",
   "shortUrl": "http://localhost:8080/url/-hMEQc"
   }


2. Redirect to Original URL
   URL: /api/redirect/{shortUrl}
   Method: GET
   Response: Redirects to the original URL.

3. Get Top 3 Most Frequently Shortened Domains
   URL: /api/top-domains
   Method: GET

Response:
   [
      {
         "www.google.com": 1
      },
      {
         "www.youtube.com": 2
      },
      {
         "www.example.com": 3
      }
   ]

