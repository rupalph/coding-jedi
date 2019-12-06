package com.webcrawler;

import com.webcrawler.controller.ParallelWebCrawlerImpl;
import com.webcrawler.controller.WebCrawler;

/**
 * Created by rupalph on 11/7/19.
 */
public class Main {
    static {
        String path = Main.class.getClassLoader()
                .getResource("logging.properties")
                .getFile();
        System.setProperty("java.util.logging.config.file", path);
    }
    public static void main(String[] args) {
        if(args.length!=1)
            System.out.println("USAGE: java -cp <jarfile> com.webcrawler.Main <URL to crawl>\n. For example java -cp <jarfile> com.webcrawler.Main https://www.cnn.com/");
        WebCrawler crawler = new ParallelWebCrawlerImpl();
        crawler.startToCrawl(args[0]);
        crawler.end();
        crawler.retrieveResult().forEach(x->System.out.print(x));

    }


}
