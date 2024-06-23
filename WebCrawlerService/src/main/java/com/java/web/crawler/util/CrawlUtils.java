package com.java.web.crawler.util;

import java.net.URI;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class CrawlUtils {

    /**
     * Parses the given URL and returns the corresponding UriComponents.
     *
     * @param url the URL to be parsed
     * @return the UriComponents representing the parsed URL
     */
    public UriComponents parseUri(String url) {
        // or I think to check for the host/domain within the first segment
        UriComponentsBuilder builder = UriComponentsBuilder.fromUri(URI.create(url));
        return builder.buildAndExpand();
    }

    /**
     * Prints the contents of a map in a pretty format.
     *
     * @param urlMap the map containing URLs as keys and sets of URLs as values
     */
    public void prettyPrintMap(Map<String, Set<String>> urlMap) {
        for (Map.Entry<String, Set<String>> entry : urlMap.entrySet()) {
            // System.out.println("Key = " + entry.getKey() + ", \n Value = " +
            // entry.getValue().size());
            System.out.println(entry.getKey());
            int count = 0;
            for (String url : entry.getValue()) {
                System.out.println(" \t \t --  " + url);
                count++;
                if (count > 5) {
                    break;
                }
            }

        }

    }

}
