package com.webcrawler.config;

import java.util.List;

/**
 * Created by rupalph on 11/11/19.
 */
public class AppConfig {
    private int numberOfThreads;
    private int batchSize;
    private long timeoutMinutes;
    private int maxUrls;
    private int queueSize;
    private List<String> urlPrefixs;
    private List<String> urlExcludePatterns;

    public AppConfig() {
        this.numberOfThreads = 10;
        this.maxUrls = 1000;
        this.batchSize = 5;
        timeoutMinutes = 3600l;
        this.queueSize = 1000;
    }

    public int getNumberOfThreads() {
        return numberOfThreads;
    }

    public void setNumberOfThreads(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }

    public int getMaxUrls() {
        return maxUrls;
    }

    public void setMaxUrls(int maxUrls) {
        this.maxUrls = maxUrls;
    }


    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    public long getTimeoutMinutes() {
        return timeoutMinutes;
    }

    public void setTimeoutMinutes(long timeoutMinutes) {
        this.timeoutMinutes = timeoutMinutes;
    }

    public List<String> getUrlPrefixs() {
        return urlPrefixs;
    }

    public void setUrlPrefixs(List<String> urlPrefixs) {
        this.urlPrefixs = urlPrefixs;
    }

    public List<String> getUrlExcludePatterns() {
        return urlExcludePatterns;
    }

    public void setUrlExcludePatterns(List<String> urlExcludePatterns) {
        this.urlExcludePatterns = urlExcludePatterns;
    }
}
