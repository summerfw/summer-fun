package summer.fun.http.client.impl;

import summer.fun.http.client.HttpClient;
import summer.fun.http.client.ClientException;
import summer.fun.http.message.Request;
import summer.fun.http.message.Response;

import java.net.URI;

public class SummerHttpClient implements HttpClient {
    private String url;

    private SummerHttpClient(Builder builder) {

    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private URI uri;

        public Builder get(Get get) {
            this.uri = get.get();
            return this;
        }

        public SummerHttpClient build() {
            return new SummerHttpClient(this);
        }
    }

    @Override
    public Response send(Request request) throws ClientException {
        return null;
    }
}
