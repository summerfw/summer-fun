package sample.controller;

import summer.fun.http.HttpRequest;
import summer.fun.http.HttpResponse;

public class Controller {
    protected HttpRequest request;
    protected HttpResponse response;

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public HttpResponse getResponse() {
        return response;
    }

    public void setResponse(HttpResponse response) {
        this.response = response;
    }
}
