package com.java.web.crawler.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.web.crawler.controller.WebCrawlerController;
import com.java.web.crawler.util.CrawlUtils;

/**
 * This class represents a web crawler service that is responsible for crawling web pages and extracting URLs.
 * It uses Jsoup library for HTML parsing and crawling functionality.
 */
@Service
public class WebCrawlerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebCrawlerController.class);

    @Autowired
    private CrawlUtils crawlUtils;
    
    /*
    * The regex pattern to match URLs in the HTML content.
    */
    private static final String PATTERN_STR = "\\b((https|http?)://[-a-zA-Z\\d+&@#/%?=~_|!:,.;]*[-a-zA-Z\\d+&@#/%=~_|])";
    private Pattern PATTERN = Pattern.compile(PATTERN_STR);
    private static final int MAX_DEPTH = 2;

    private boolean isDomainOnly = true;
    private Set<String> visitedUrls = null;
    private Map<String, Set<String>> urlMap;
    private String domainName = null;


    /**
     * Crawls the web starting from the specified root URL and returns a map of URLs and their associated links.
     *
     * @param rootURL the root URL from which to start crawling
     * @param isDomainOnly a flag indicating whether to crawl only within the same domain as the root URL
     * @param depth the maximum depth of crawling
     * @return a map of URLs and their associated links
     */
    public Map<String, Set<String>> crawl(String rootURL, boolean isDomainOnly, int depth) {
        this.isDomainOnly = isDomainOnly;
        visitedUrls = new HashSet<>();
        urlMap = new HashMap<>();
        domainName = crawlUtils.parseUri(rootURL).getHost();
        LOGGER.info(" DOMAIN_URL :: " + domainName);
        crawl(rootURL, depth);
        LOGGER.info("Successfully crawled! ... " + rootURL);
        crawlUtils.prettyPrintMap(urlMap);
        return urlMap;
    }

    /**
     * Recursive function which Crawls the given URL and its linked pages up to a specified depth.
     * 
     * @param url   The URL to crawl.
     * @param depth The depth of the crawl.
     */
    private void crawl(String url, int depth) {

        if (urlMap.size() > 5) {
            crawlUtils.prettyPrintMap(urlMap);
            return;
        }

        if (depth > MAX_DEPTH || visitedUrls.contains(url) || !url.contains(domainName)) {
            // System.out.println(" Current URL :: "+url);
            return;
        }
        LOGGER.info("Crawling ....  " + url);
        
        try {
            Document document = Jsoup.connect(url).get();
            visitedUrls.add(url);

            Elements links = document.select("a[href]");
            for (Element link : links) {
                String linkAttr = link.attr("href");
                String nextUrl = link.absUrl("href");
                if (!linkAttr.startsWith("http")) {
                    updateUrlMap(linkAttr, nextUrl);
                }
                crawl(nextUrl, depth + 1);
            }
        } catch (IOException e) {
            LOGGER.error("Error crawling: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.error(" Error Crawling URL :: " + e.getMessage());
        }

    }

    /**
     * Updates the URL map with the given link attribute and next URL.
     * If the link attribute is empty or has a length less than or equal to 1, the method returns without making any updates.
     * The link attribute is used as the key in the URL map.
     * If the link attribute starts with a forward slash ("/"), it is removed before using it as the key.
     * If the link attribute contains a forward slash ("/"), only the substring before 
     *          the first occurrence of the forward slash is used as the key.
     * The next URL is added to the set of URLs associated with the key in the URL map.
     * If the key already exists in the URL map, the next URL is added to the existing set of URLs.
     * If the key does not exist in the URL map, a new set is created with the next URL and added to the URL map.
     *
     * @param linkAttr the link attribute
     * @param nextUrl  the next URL
     */
    private void updateUrlMap(String linkAttr, String nextUrl) {
        if (linkAttr.length() <= 1) {
            return;
        }
        String key = null;
        if (linkAttr.startsWith("/")) {
            linkAttr = linkAttr.substring(1);
        }
        if (linkAttr.contains("/")) {
            key = linkAttr.substring(0, linkAttr.indexOf("/"));
        } else {
            key = linkAttr;
        }

        Set<String> set = urlMap.get(key);
        if (set != null) {
            set.add(nextUrl);
        } else {
            set = new HashSet<>();
            set.add(nextUrl);
            urlMap.put(key, set);
        }

    }

    /*
     *   Getter and Setters 
     */

    public boolean isDomainOnly() {
        return isDomainOnly;
    }

    public void setDomainOnly(boolean isDomainOnly) {
        this.isDomainOnly = isDomainOnly;
    }
    
    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public CrawlUtils getCrawlUtils() {
        return crawlUtils;
    }

    public void setCrawlUtils(CrawlUtils crawlUtils) {
        this.crawlUtils = crawlUtils;
    }

    public static String getPatternStr() {
        return PATTERN_STR;
    }

    public Pattern getPATTERN() {
        return PATTERN;
    }

    public void setPATTERN(Pattern pATTERN) {
        PATTERN = pATTERN;
    }

    public Set<String> getVisitedUrls() {
        return visitedUrls;
    }

    public void setVisitedUrls(Set<String> visitedUrls) {
        this.visitedUrls = visitedUrls;
    }

    public Map<String, Set<String>> getUrlMap() {
        return urlMap;
    }

    public void setUrlMap(Map<String, Set<String>> urlMap) {
        this.urlMap = urlMap;
    }

    public static int getMaxDepth() {
        return MAX_DEPTH;
    }

}
