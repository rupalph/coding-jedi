package com.webcrawler.util;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by rupalph on 11/7/19.
 */
public class WebContentDownloader {
    private static final Logger LOGGER = Logger.getLogger( WebContentDownloader.class.getName() );

    public static List<String> downloadPage(String urlString) throws Exception {

        List<String> content = new ArrayList<>();

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
           try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                content = reader.lines().collect(Collectors.toList());
            }
        }catch (Exception e){
            //ignore exception, do not download content if can't load
            LOGGER.log(Level.FINE, "DEBUG: Error retrieving "+urlString+", error: "+e.getMessage());
        }
        return content;

    }

}

