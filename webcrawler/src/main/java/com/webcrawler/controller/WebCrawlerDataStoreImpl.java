package com.webcrawler.controller;

import com.webcrawler.domain.Website;
import com.webcrawler.dao.WebCrawlerDataStore;

import java.util.*;

/**
 * Stores in memory all crawled url for simplicity.
 */
public class WebCrawlerDataStoreImpl implements WebCrawlerDataStore {

    LinkedHashMap<String, Set<String>> allWebSites;

    public WebCrawlerDataStoreImpl() {

        this.allWebSites = new LinkedHashMap<>();
    }

    @Override
    public void save(String parentUrl, String url) {
        if(!parentUrl.equals("#"))
            allWebSites.computeIfAbsent(parentUrl, k -> new HashSet<>()).add(url);
            //System.out.print(input);
    }

    @Override
    public List<Website> getAllUrls() {
        List<Website> result = new ArrayList<>();
        for(String key:allWebSites.keySet()){
            Website website = new Website(key, new ArrayList<>(allWebSites.get(key)));
            result.add(website);
        }
        return result;
    }

}