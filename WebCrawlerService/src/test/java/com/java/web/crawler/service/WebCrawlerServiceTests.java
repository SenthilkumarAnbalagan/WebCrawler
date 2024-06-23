package com.java.web.crawler.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;

import org.junit.Before;
import org.junit.Test;

public class WebCrawlerServiceTests {

    private WebCrawlerService webCrawlerService;

    @Before
    public void setUp() {
        webCrawlerService = new WebCrawlerService();
        webCrawlerService.setDomainName("example.com");
        webCrawlerService.setDomainOnly(true); // Assuming setter method exists
    }

    @Test
    public void testUrlPattern() {
        String testUrl = "https://example.com/test_abc";
        Matcher matcher = webCrawlerService.getPATTERN().matcher(testUrl);
        assertTrue("The URL should match the pattern", matcher.matches());
    }

    @Test
    public void testVisitedUrlsInitialization() {
        assertNull("Initially, visitedUrls should be null", webCrawlerService.getVisitedUrls());
    }

    @Test
    public void testDomainNameSetCorrectly() {
        assertEquals("The domain name should be set to example.com", "example.com", webCrawlerService.getDomainName());
    }

    @Test
    public void testIsDomainOnlyFlag() {
        assertTrue("isDomainOnly flag should be true", webCrawlerService.isDomainOnly());
    }

}