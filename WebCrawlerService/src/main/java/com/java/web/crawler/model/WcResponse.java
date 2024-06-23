package com.java.web.crawler.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

/**
 * Represents a response object for the web crawler service.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WcResponse {
     private String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Object reason;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Object data;
    private int code;
}
