package com.java.wc.client.bean;

/**
 * The WcRequest class represents a web crawler request.
 * It contains information such as the URL to crawl, the depth of the crawl, and whether to crawl only within the domain.
 */
public class WcRequest {

    private String url;
    private int depth;
    private boolean isDomainOnly;
    
    /**
     * Gets the URL to crawl.
     * @return The URL to crawl.
     */
    public String getUrl() {
        return url;
    }
    
    /**
     * Sets the URL to crawl.
     * @param url The URL to crawl.
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    /**
     * Gets the depth of the crawl.
     * @return The depth of the crawl.
     */
    public int getDepth() {
        return depth;
    }
    
    /**
     * Sets the depth of the crawl.
     * @param depth The depth of the crawl.
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }
    
    /**
     * Checks if the crawl should be limited to the domain only.
     * @return True if the crawl should be limited to the domain only, false otherwise.
     */
    public boolean isDomainOnly() {
        return isDomainOnly;
    }
    
    /**
     * Sets whether the crawl should be limited to the domain only.
     * @param isDomainOnly True to limit the crawl to the domain only, false otherwise.
     */
    public void setDomainOnly(boolean isDomainOnly) {
        this.isDomainOnly = isDomainOnly;
    }
}
