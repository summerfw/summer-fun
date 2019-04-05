package summer.fun.http.message.impl;

import org.glassfish.grizzly.http.server.Response;
import summer.fun.http.message.Message;
import summer.fun.http.message.Stream;

import java.util.Map;

public class HttpMessage implements Message {
    private org.glassfish.grizzly.http.server.Response grizzlyResponse;
    private String protocolVersion = "1.1";
    private Map<String, String> headers;
    private Stream body;

    public HttpMessage() {}

    public HttpMessage(org.glassfish.grizzly.http.server.Response grizzlyResponse) {
        this.grizzlyResponse = grizzlyResponse;
    }

    public Response getGrizzlyResponse() {
        return grizzlyResponse;
    }

    public void setGrizzlyResponse(Response grizzlyResponse) {
        this.grizzlyResponse = grizzlyResponse;
    }

    @Override
    public String getProtocolVersion() {
        return this.protocolVersion;
    }

    @Override
    public Message withProtocolVersion(String version) {
        this.protocolVersion = version;
        return this;
    }

    @Override
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public boolean hasHeader(String name) {
        return this.headers.containsKey(name);
    }

    @Override
    public String[] getHeader(String name) {
        return new String[0];
    }

    @Override
    public String getHeaderLine(String name) {
        return null;
    }

    @Override
    public Message withHeader(String name, String value) throws IllegalArgumentException {
        this.headers.put(name, value);
        return this;
    }

    @Override
    public Message withHeader(String name, String[] value) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Message withAddedHeader(String name, String value) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Message withAddedHeader(String name, String[] value) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Message withoutHeader(String name) {
        return null;
    }

    @Override
    public Stream getBody() {
        return this.body;
    }

    @Override
    public Message withBody(Stream body) throws IllegalArgumentException {
        this.body = body;
        return this;
    }
}
