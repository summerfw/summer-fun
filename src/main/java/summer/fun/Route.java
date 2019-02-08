package summer.fun;

import summer.fun.http.Http;

/**
 * Route
 */
public class Route {
    private final Http.Method httpMethod;
    private final String path;
    private final Object handler;

    public Route(Http.Method httpMethod, String path, Object handler) {
        this.httpMethod = httpMethod;
        this.path = path;
        this.handler = handler;
    }

    public Http.Method getHttpMethod() {
        return httpMethod;
    }

    public String getPath() {
        return path;
    }

    public Object getHandler() {
        return handler;
    }

}