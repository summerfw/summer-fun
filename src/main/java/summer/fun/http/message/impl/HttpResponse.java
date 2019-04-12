package summer.fun.http.message.impl;

import summer.fun.http.HttpStatus;
import summer.fun.http.message.Response;

public class HttpResponse extends HttpMessage implements Response {
    private org.glassfish.grizzly.http.server.Response grizzlyResponse;

    public HttpResponse() {}

    public HttpResponse(org.glassfish.grizzly.http.server.Response grizzlyResponse) {
        super(grizzlyResponse);
        this.grizzlyResponse = grizzlyResponse;
    }

    public org.glassfish.grizzly.http.server.Response getGrizzlyResponse() {
        return grizzlyResponse;
    }

    public void setGrizzlyResponse(org.glassfish.grizzly.http.server.Response grizzlyResponse) {
        this.grizzlyResponse = grizzlyResponse;
    }

    @Override
    public int getStatus() {
        return this.grizzlyResponse.getStatus();
    }

    @Override
    public Response withStatus(int code) throws IllegalArgumentException {
        this.grizzlyResponse.setStatus(code);
        return this;
    }

    @Override
    public Response withStatus(HttpStatus status) throws IllegalArgumentException {
        this.grizzlyResponse.setStatus(org.glassfish.grizzly.http.util.HttpStatus.getHttpStatus(status.getCode()));
        return this;
    }

    @Override
    public Response withStatus(int code, String reasonPhrase) throws IllegalArgumentException {
        this.grizzlyResponse.setStatus(code, reasonPhrase);
        return this;
    }

    @Override
    public String getReasonPhrase() {
        return this.grizzlyResponse.getMessage();
    }

    @Override
    public Response withReasonPhrase(String reasonPhrase) throws IllegalArgumentException {
        return null;
    }
}
