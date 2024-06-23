package com.java.wc.client.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.java.wc.client.bean.Response;
import com.java.wc.client.bean.WcRequest;

/**
 * The CrawlHttpClient class is responsible for sending HTTP POST requests to a web crawler server.
 */
public class CrawlHttpClient {

    private String serverHost;
    private int serverPort;
    // setting default
    private String protocol = "http";

    /**
     * Constructs a CrawlHttpClient object with the specified server host and port.
     * 
     * @param serverHost the host of the web crawler server
     * @param serverPort the port of the web crawler server
     */
    public CrawlHttpClient(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    /**
     * Gets the protocol used for the HTTP requests.
     * 
     * @return the protocol
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Sets the protocol used for the HTTP requests.
     * 
     * @param protocol the protocol to set
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * Sends an HTTP POST request to the web crawler server with the specified URL, domain-only flag, and depth.
     * 
     * @param url the URL to crawl
     * @param isDomainOnly true if only the domain should be crawled, false otherwise
     * @param depth the depth of the crawl
     * @return a map containing the crawled URLs and their associated links
     */
    public Map<String, List<String>> sendHttpPostRequest(String url, boolean isDomainOnly, int depth) {
        URL serverUrl;
        Map<String, List<String>> map = Collections.EMPTY_MAP;
        try {
            serverUrl = new URL(getServerURL());
            HttpURLConnection httpClient = (HttpURLConnection) serverUrl.openConnection();
            httpClient.setRequestMethod("POST");
            httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
            httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            httpClient.setRequestProperty("Content-Type", "application/json");
            httpClient.setDoOutput(true);

            WcRequest request = new WcRequest();
            request.setUrl(url);
            request.setDepth(depth);
            request.setDomainOnly(isDomainOnly);

            Gson gson = new Gson();
            String jsonPayload = gson.toJson(request);

            try (OutputStream os = httpClient.getOutputStream()) {
                byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            httpClient.connect();
            int responseCode = httpClient.getResponseCode();
            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpClient.getInputStream()));
                StringBuilder jsonContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("Line ::" + line);
                    jsonContent.append(line).append("\n");
                }
                reader.close();
                Response response = gson.fromJson(jsonContent.toString(), Response.class);
                if (response.getCode() == 200) {
                    map = gson.fromJson(response.getData(), Map.class);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(" Exception " + e);
        }
        return map;
    }

    private String getServerURL() {
        String serverUrl = protocol + "://" + serverHost + ":" + serverPort + "/crawl";
        return serverUrl;
    }
}
