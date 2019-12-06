package com.webcrawler.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.webcrawler.domain.Website;
import com.webcrawler.config.AppConfig;
import com.webcrawler.dao.WebCrawlerDataStore;
import com.webcrawler.domain.CrawlItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 * Crawls website parallel using ThreadPool
 * The configuration is in com.webcrawler.config.yaml file
 * current com.webcrawler.config set to 10 threads and 20 crawl at a time out of 1 minute
 * and crawls max 1000 urls.
 *
 *
 */

public class ParallelWebCrawlerImpl implements WebCrawler {
    private static final Logger LOGGER = Logger.getLogger( ParallelWebCrawlerImpl.class.getName() );

    private WebCrawlerDataStore dataStore;
    LinkedBlockingDeque<CrawlItem> tobeCrawled;
    Set<String> visitedUrls;
    ExecutorService executor;
    private AppConfig appConfig;


    public ParallelWebCrawlerImpl() {
        appConfig = readConfig();
        tobeCrawled = new LinkedBlockingDeque<>(appConfig.getQueueSize());
        dataStore = new WebCrawlerDataStoreImpl();
        executor = Executors.newFixedThreadPool(appConfig.getNumberOfThreads());
        visitedUrls = new HashSet<>();
    }

    private AppConfig readConfig() {
        AppConfig appConfig = new AppConfig();
        try {
            InputStream inputStream = getClass().getResourceAsStream("/config.yaml");
            final ObjectMapper mapper = new ObjectMapper(new YAMLFactory()); // jackson databind
            appConfig = mapper.readValue(inputStream, AppConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appConfig;
    }

    /**
     * com.webcrawler.Main logic is to create parallel threads and add work to linkedblocking queue
     * <p>
     * As long as the worker queue is not empty or max-urls crawed is not reached,
     * it keeps creating new tasks
     *
     * @param rootUrl
     */
       public void startToCrawl(String rootUrl) {
        tobeCrawled.offer(new CrawlItem("#", rootUrl));

        long starttime = System.currentTimeMillis();

        while(!tobeCrawled.isEmpty() && visitedUrls.size() < appConfig.getMaxUrls()) {

            List<Future<Boolean>> list = new ArrayList<>();
            for (int i = 0; i < appConfig.getNumberOfThreads(); i++) {
                WebCrawlerCallableTask task = new WebCrawlerCallableTask(tobeCrawled, visitedUrls, dataStore, appConfig);
                Future<Boolean> future = executor.submit(task);
                list.add(future);
            }

            try {
                for (Future<Boolean> future : list) {
                    future.get();
                }
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, "Exception caught while retrieving result ", e);
                list.forEach(k->k.cancel(true));

            }
            long now = System.currentTimeMillis();
            if(now - starttime >= appConfig.getTimeoutMinutes()*60000) {
                break;
            }
        }
    }

    public void end() {
        executor.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            while (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                    LOGGER.log(Level.FINE, "Pool did not terminate, shutdown now.");
                    executor.shutdownNow();
                }
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            executor.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }

    }

    public List<Website> retrieveResult() {

        return dataStore.getAllUrls();
    }
}
