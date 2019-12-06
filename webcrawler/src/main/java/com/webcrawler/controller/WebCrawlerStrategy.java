package com.webcrawler.controller;

import com.webcrawler.domain.Website;

interface WebCrawlerStrategy  {
    Website crawl(String rootUrl);
}
