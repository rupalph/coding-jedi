package com.webcrawler.domain;

/**
 * Created by rupalph on 11/7/19.
 */
public class CrawlItem {
    
    String parentUrl;
    String websiteUrl;

    public CrawlItem(String parentUrl, String websiteUrl) {
        this.parentUrl = parentUrl;
        this.websiteUrl = websiteUrl;
    }

    public String getParentUrl() {
        return parentUrl;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public String toString() {
        return parentUrl+ " "+websiteUrl;
    }
}
