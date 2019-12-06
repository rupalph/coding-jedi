package com.webcrawler.controller;

import com.webcrawler.domain.Website;

import java.util.List;

/**
 * Created by rupalph on 11/11/19.
 */
public interface WebCrawler {
    void startToCrawl(String rootUrl);

    void end();

    List<Website> retrieveResult();


}