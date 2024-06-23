package com.java.web.crawler.controller;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.java.web.crawler.model.WcRequest;
import com.java.web.crawler.model.WcResponse;
import com.java.web.crawler.service.WebCrawlerService;

/**
 * This class is the controller for the web crawler service.
 * It handles the HTTP requests and delegates the crawling process to the WebCrawlerService.
 */
@RestController
public class WebCrawlerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebCrawlerController.class);
    @Autowired
    private WebCrawlerService webCrawlerService;

    private boolean isServiceRunning = true;

    /**
     * Crawls the given URL and retrieves information based on the provided crawl request.
     *
     * @param crawlRequest The crawl request containing the URL, domain flag, and depth.
     * @return A ResponseEntity containing the crawl response.
     * @throws Exception If an error occurs during the crawling process.
     */
    @PostMapping("/crawl")
    public ResponseEntity<WcResponse> crawl(@RequestBody WcRequest crawlRequest) throws Exception {
        LOGGER.info("getting info for URL: {}, isDomain : {} , depth : {}", 
                    crawlRequest.getUrl(), crawlRequest.isDomainOnly(), crawlRequest.getDepth());
        Map<String, Set<String>> resultMap = webCrawlerService.crawl(crawlRequest.getUrl(),
                crawlRequest.isDomainOnly(), crawlRequest.getDepth());
        
        if (resultMap != null && resultMap.size() != 0) {
            Gson gson = new Gson();
            String jsonString = gson.toJson(resultMap);
            return ResponseEntity.ok(WcResponse.builder()
                    .message("Successfully crawled!")
                    .data(jsonString)
                    .code(200)
                    .build());
        } else {
            return ResponseEntity.ok(WcResponse.builder()
                    .message("Failed to crawl!")
                    .data("Unable to crawl the given URL ")
                    .code(500)
                    .build());

        }
    }

    /**
     * Retrieves the health status of the service. To be further enhanced.
     * 
     * @return A ResponseEntity object containing the health status of the service.
     *         If the service is running, it returns "Up" with HTTP status code 200 (OK).
     *         If the service is not running, it returns "Down" with HTTP status code 500 (Internal Server Error).
     */
    @GetMapping("/gethealth")
    @ResponseBody
    public ResponseEntity<String> getServiceHealth() {
        // To be enhanced 
        if (isServiceRunning) {
            return new ResponseEntity<String>("Up", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Down", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
