package com.example.demo_redis.controller;

public class ApiResponse<T> {
    private int status;
    private T response;
    private String responseMessage;

    public ApiResponse(int status, T response, String responseMessage) {
        this.status = status;
        this.response = response;
        this.responseMessage = responseMessage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}

