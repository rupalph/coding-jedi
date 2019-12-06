package com.webcrawler.controller;

import com.webcrawler.domain.Website;
import com.webcrawler.config.AppConfig;
import com.webcrawler.dao.WebCrawlerDataStore;
import com.webcrawler.domain.CrawlItem;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Callable task to download website content, filter and add new links to processing queue.
 *
 */
public class WebCrawlerCallableTask implements Callable<Boolean> {
    private static final Logger LOGGER = Logger.getLogger( WebCrawlerCallableTask.class.getName() );

    LinkedBlockingDeque<CrawlItem> processingQueue;
    Set<String> visitedUrls;
    WebCrawlerDataStore dataStore;
    AppConfig appConfig;
    public WebCrawlerCallableTask(LinkedBlockingDeque<CrawlItem> queue, Set<String> visited, WebCrawlerDataStore ds, AppConfig appConfig){
        this.processingQueue = queue;
        this.visitedUrls = visited;
        this.dataStore = ds;
        this.appConfig = appConfig;
    }
    public boolean run() {
        for(int i = 0; i<appConfig.getBatchSize();i++){
            if(processingQueue.isEmpty() || visitedUrls.size() >= appConfig.getMaxUrls()) {
                break;
            }
            else {
                CrawlItem pair = processingQueue.poll();
                if (pair != null && !visitedUrls.contains(pair.getWebsiteUrl())) {
                    //download page and extract urls
                    WebCrawlerStrategy strategy = new SimpleWebCrawlerStrategyImpl(appConfig.getUrlPrefixs(), appConfig.getUrlExcludePatterns());
                    Website w = strategy.crawl(pair.getWebsiteUrl());
                    w.setParentUrl(pair.getParentUrl());
                    String url = w.getWebsiteUrl();

                    //filter urls (remove already visited/crawled urls)
                    List<CrawlItem> newUrls = w.getLinkedWebsites().stream().filter(k -> !visitedUrls.contains(k)).map(v -> new CrawlItem(url, v)).collect(Collectors.toList());

                    //if still some left, add to processing queue
                    if (newUrls.size() > 0) {

                        //find out processing queue capacity
                        int capacity = processingQueue.remainingCapacity();

                        if (newUrls.size() > capacity)
                            newUrls.subList(capacity, newUrls.size()).clear();

                        //add urls to processing queue, mark them visited and persist result
                        processingQueue.addAll(newUrls);
                        visitedUrls.add(pair.getWebsiteUrl());
                        dataStore.save(pair.getParentUrl(), pair.getWebsiteUrl());

                        LOGGER.log(Level.FINE, "DEBUG: crawling done for " + pair.getWebsiteUrl());
                    }


                }
            }
        }
        //return true if able to crawl maxUrls
        return true;
    }

    @Override
    public Boolean call() throws Exception {
        return run();
    }
}