package com.webcrawler.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rupalph on 11/7/19.
 */
public class Website {

    String parentUrl;
    String websiteUrl;
    List<String> linkedWebsites;

    public Website(String parent, String url) {
        this.parentUrl = parent;
        this.websiteUrl = url;
        linkedWebsites = new ArrayList<String>();
    }

    public Website(String url, List<String> tobeCrawled) {
        this.websiteUrl = url;
        this.linkedWebsites = tobeCrawled;
    }

    public void add(String website) {
        linkedWebsites.add(website);
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }
    public List<String> getLinkedWebsites() { return linkedWebsites; }
    public String getParentUrl() {
        return parentUrl;
    }

    public void setParentUrl(String parentUrl) {
        this.parentUrl = parentUrl;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(websiteUrl+"\n");
        for(String w:linkedWebsites){
            sb.append("\t"+w+"\n");
        }
        return sb.toString();
    }
}
