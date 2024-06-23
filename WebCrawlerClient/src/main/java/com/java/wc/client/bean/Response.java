package com.java.wc.client.bean;

public class Response {

    private String message;
    private String data;
    private int code;

    /**
     * Constructs a Response object with the specified message, data, and code.
     *
     * @param message the message associated with the response
     * @param data the data associated with the response
     * @param code the code associated with the response
     */
    public Response(String message, String data, int code) {
        this.message = message;
        this.data = data;
        this.code = code;
    }

    /**
     * Constructs an empty Response object.
     */
    public Response() {
    }

    /**
     * Returns the message associated with the response.
     *
     * @return the message associated with the response
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message associated with the response.
     *
     * @param message the message to be set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the data associated with the response.
     *
     * @return the data associated with the response
     */
    public String getData() {
        return data;
    }

    /**
     * Sets the data associated with the response.
     *
     * @param data the data to be set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Returns the code associated with the response.
     *
     * @return the code associated with the response
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets the code associated with the response.
     *
     * @param code the code to be set
     */
    public void setCode(int code) {
        this.code = code;
    }
}
