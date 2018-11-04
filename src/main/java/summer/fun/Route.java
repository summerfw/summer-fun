package summer.fun;

import java.util.function.BiConsumer;

/**
 * Route
 */
public class Route {
    private final String httpMethod;
    private final String path;
    private final BiConsumer<Request, Response> handler;

    public Route(String httpMethod, String path, BiConsumer<Request, Response> handler) {
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

    public BiConsumer<Request, Response> getHandler() {
        return handler;
    }

}