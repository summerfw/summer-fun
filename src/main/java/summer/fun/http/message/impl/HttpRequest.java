package summer.fun.http.message.impl;

import summer.fun.http.message.Request;
import summer.fun.http.message.Uri;

public class HttpRequest extends HttpMessage implements Request {
    private String requestTarget;
    private String method;

    public HttpRequest() {}

    public HttpRequest(Builder builder) {}

    public HttpRequest(org.glassfish.grizzly.http.server.Request grizzlyRequest) {
        super(grizzlyRequest.getResponse());
    }

    @Override
    public String getRequestTarget() {
        return null;
    }

    @Override
    public Request withRequestTarget(Object requestTarget) {
        return null;
    }

    @Override
    public String getMethod() {
        return null;
    }

    @Override
    public Request withMethod(String method) throws IllegalArgumentException {
        return this;
    }

    @Override
    public Uri getUri() {
        return null;
    }

    @Override
    public Request withUri(Uri uri) {
        return null;
    }

    @Override
    public Request withUri(Uri uri, boolean preserveHost) {
        return null;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String requestTarget;
        private String method;

        public Builder withRequestTarget(String requestTarget) {
            this.requestTarget = requestTarget;
            return this;
        }

        public Builder withMethod(String method) {
            this.method = method;
            return this;
        }

        public HttpRequest build() {
            return new HttpRequest(this);
        }
    }
}
