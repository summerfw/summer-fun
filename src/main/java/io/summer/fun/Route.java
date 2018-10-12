package io.summer.fun;

/**
 * Route
 */
public class Route {
    private final String httpMethod;
    private final String path;
    private final Handler handler;

    public Route(String httpMethod, String path, Handler handler) {
        this.httpMethod = httpMethod;
        this.path = path;
        this.handler = handler;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getPath() {
        return path;
    }

    public Handler getHandler() {
        return handler;
    }

}