package com.java.web.crawler.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.java.web.crawler.model.WcRequest;
import com.java.web.crawler.model.WcResponse;
import com.java.web.crawler.service.WebCrawlerService;

public class WebCrawlerControllerTest {

    private WebCrawlerController webCrawlerController;
    @Autowired
    private WebCrawlerService webCrawlerService;

    @Before
    public void setup() {
        webCrawlerService = mock(WebCrawlerService.class);
        webCrawlerController = new WebCrawlerController();
        //webCrawlerController.setWebCrawlerService(webCrawlerService);
    }

    @Test
    public void testCrawl_Success() throws Exception {
        // Arrange
        WcRequest request = new WcRequest();
        request.setUrl("https://example.com");
        request.setDomainOnly(true);
        request.setDepth(2);

        Map<String, Set<String>> resultMap = new HashMap<>();
        Set<String> links = new HashSet<>();
        links.add("https://example.com/page1");
        links.add("https://example.com/page2");
        resultMap.put("https://example.com", links);

        when(webCrawlerService.crawl(request.getUrl(), request.isDomainOnly(), request.getDepth()))
                .thenReturn(resultMap);

        // Act
        ResponseEntity<WcResponse> response = webCrawlerController.crawl(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Successfully crawled!", response.getBody().getMessage());
        assertEquals("{\"https://example.com\":[\"https://example.com/page1\",\"https://example.com/page2\"]}",
                response.getBody().getData());
        assertEquals(200, response.getBody().getCode());
    }

    @Test
    public void testCrawl_Failure() throws Exception {
        // Arrange
        WcRequest request = new WcRequest();
        request.setUrl("https://example.com");
        request.setDomainOnly(true);
        request.setDepth(2);

        when(webCrawlerService.crawl(request.getUrl(), request.isDomainOnly(), request.getDepth()))
                .thenReturn(null);

        // Act
        ResponseEntity<WcResponse> response = webCrawlerController.crawl(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Failed to crawl!", response.getBody().getMessage());
        assertEquals("Unable to crawl the given URL ", response.getBody().getData());
        assertEquals(500, response.getBody().getCode());
    }
}