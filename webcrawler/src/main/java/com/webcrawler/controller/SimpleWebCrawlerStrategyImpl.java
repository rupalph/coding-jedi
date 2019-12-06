package com.webcrawler.controller;

import com.webcrawler.util.WebContentDownloader;
import com.webcrawler.domain.Website;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Crawls the url : download contents, filters by "a href" and http (can be configured in com.webcrawler.config.xml)
 * Also excludes some of link tyes for e.g image, video (configurable from com.webcrawler.config.xml)
 */
public class SimpleWebCrawlerStrategyImpl implements WebCrawlerStrategy {

    List<String> urlPrefix;
    List<String> urlExcludePattern;
    public SimpleWebCrawlerStrategyImpl(List<String> prefix, List<String> pattern) {
        urlPrefix = prefix;
        urlExcludePattern = pattern;
    }

    public List<String> extractUrls(List<String> response) {
        Set<String> visited = new HashSet<>();
        List<String> websites = new ArrayList<>();
        for(String line: response){
            if(isUrl(line)) {
                try {
                    String url2 = line.substring(line.indexOf("http"));
                    url2 = url2.substring(0, url2.indexOf("\""));

                    if (!visited.contains(url2) && isValidLink(url2)) {
                        websites.add(url2);
                        visited.add(url2);
                    }
                }catch (StringIndexOutOfBoundsException e){
                    //do nothing, ignore
                }
            }
        }
        return websites;
    }

    private boolean isUrl(String line) {
        for(String prefix:urlPrefix){
            if(!line.contains(prefix))
                return false;
        }
        return true;
    }

    private boolean isValidLink(String url) {
        for(String pattern:urlExcludePattern){
            if(url.contains(pattern))
                return false;
        }
        return true;
    }

    public List<String> getWebsiteContent(String url) {
        List<String> response = null;
        try {
            response = WebContentDownloader.downloadPage(url);
            //String response2 = WebContentDownloader.getUrlAsString(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private List<String> findLinks(String url){
        return extractUrls(getWebsiteContent(url));
    }

    @Override
    public Website crawl(String url) {

        List<String> tobeCrawled = findLinks(url);
        Website website = new Website(url, tobeCrawled);

        return website;
    }
}