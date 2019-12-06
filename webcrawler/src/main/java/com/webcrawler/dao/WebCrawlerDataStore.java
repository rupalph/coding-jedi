package com.webcrawler.dao;

import com.webcrawler.domain.Website;

import java.util.List;

/**
 *
 */
public interface WebCrawlerDataStore {
    void save(String parentUrl, String url);
    List<Website> getAllUrls();
}