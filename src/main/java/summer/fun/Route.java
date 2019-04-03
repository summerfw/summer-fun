package summer.fun;

import summer.fun.http.HttpMethod;
import summer.fun.http.HttpRequest;
import summer.fun.http.HttpResponse;

import java.util.function.BiConsumer;

/**
 * Route
 */
public class Route {
    private HttpMethod httpMethod;
    private String path;
    private RequestHandler handler;

    public Route(HttpMethod httpMethod, String path, RequestHandler handler) {
        this.httpMethod = httpMethod;
        this.path = path;
        this.handler = handler;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public RequestHandler getHandler() {
        return handler;
    }

    public void setHandler(RequestHandler handler) {
        this.handler = handler;
    }
}