package com.java.wc.client.main;

import java.util.List;
import java.util.Map;

import com.java.wc.client.util.CrawlUtils;

/**
 * The WebCrawlerClient class represents a client for web crawling.
 * It provides methods to configure and start the crawling process.
 */
public class WebCrawlerClient {

    private int depth;
    private boolean isDomainOnly;
    private String url;
    private String serverHost;
    private int serverPort;

    /**
     * Constructs a WebCrawlerClient object with the specified parameters.
     * 
     * @param depth        the depth of the crawling process
     * @param isDomainOnly a flag indicating whether to crawl only within the domain
     * @param url          the URL to start crawling from
     * @param serverHost   the host of the server to send HTTP requests to
     * @param port         the port of the server to send HTTP requests to
     */
    public WebCrawlerClient(int depth, boolean isDomainOnly, String url, String serverHost, int port) {
        this.depth = depth;
        this.isDomainOnly = isDomainOnly;
        this.url = url;
        this.serverHost = serverHost;
        this.serverPort = port;
    }

    private boolean isPrettyPrint = true;

    /**
     * Constructs a WebCrawlerClient object with the specified parameters.
     * This constructor does not include the server host and port.
     * 
     * @param depth        the depth of the crawling process
     * @param isDomainOnly a flag indicating whether to crawl only within the domain
     * @param url          the URL to start crawling from
     */
    public WebCrawlerClient(int depth, boolean isDomainOnly, String url) {
        this.depth = depth;
        this.isDomainOnly = isDomainOnly;
        this.url = url;
    }

    /**
     * Returns the depth of the crawling process.
     * 
     * @return the depth of the crawling process
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Sets the depth of the crawling process.
     * 
     * @param depth the depth of the crawling process
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * Returns whether the crawling process is limited to the domain only.
     * 
     * @return true if the crawling process is limited to the domain only, false
     *         otherwise
     */
    public boolean isDomainOnly() {
        return isDomainOnly;
    }

    /**
     * Sets whether the crawling process should be limited to the domain only.
     * 
     * @param isDomainOnly true to limit the crawling process to the domain only,
     *                     false otherwise
     */
    public void setDomainOnly(boolean isDomainOnly) {
        this.isDomainOnly = isDomainOnly;
    }

    /**
     * Returns the URL to start crawling from.
     * 
     * @return the URL to start crawling from
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL to start crawling from.
     * 
     * @param url the URL to start crawling from
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Returns whether the crawling results should be pretty printed.
     * 
     * @return true if the crawling results should be pretty printed, false
     *         otherwise
     */
    public boolean isPrettyPrint() {
        return isPrettyPrint;
    }

    /**
     * Sets whether the crawling results should be pretty printed.
     * 
     * @param isPrettyPrint true to pretty print the crawling results, false
     *                      otherwise
     */
    public void setPrettyPrint(boolean isPrettyPrint) {
        this.isPrettyPrint = isPrettyPrint;
    }

    /**
     * Starts the crawling process.
     * It sends an HTTP POST request to the server and prints the results.
     */
    public void startCrawling() {

        CrawlHttpClient httpClient = new CrawlHttpClient(serverHost, serverPort);
        Map<String, List<String>> resultMap = httpClient.sendHttpPostRequest(url, isDomainOnly, depth);

        if (resultMap != null && resultMap.size() > 0) {
            if (isPrettyPrint) {
                CrawlUtils.prettyPrintMap(resultMap);
            } else {
                System.out.println(resultMap);
            }
        }
    }

}
