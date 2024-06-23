/**
    * Prints the contents of a map in a pretty format.
    * 
    * @param urlMap the map containing URLs as keys and lists of strings as values
    */
package com.java.wc.client.util;

import java.util.List;
import java.util.Map; 


public class CrawlUtils {

    public static void prettyPrintMap(Map<String, List<String>> urlMap) {
        for (Map.Entry<String, List<String>> entry : urlMap.entrySet()) {
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