package summer.fun;

import summer.fun.http.HttpMethod;
import summer.fun.http.HttpRequest;
import summer.fun.http.HttpResponse;

import java.util.function.BiConsumer;

/**
 * Route
 */
public class Route {
    private final HttpMethod httpMethod;
    private final String path;
    private final BiConsumer<HttpRequest, HttpResponse> handler;

    public Route(HttpMethod httpMethod, String path, BiConsumer<HttpRequest, HttpResponse> handler) {
        this.httpMethod = httpMethod;
        this.path = path;
        this.handler = handler;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getPath() {
        return path;
    }

    public BiConsumer<HttpRequest, HttpResponse> getHandler() {
        return handler;
    }

}