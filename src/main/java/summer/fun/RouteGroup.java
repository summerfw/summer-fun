package summer.fun;

import summer.fun.http.Http;
import summer.fun.http.Request;
import summer.fun.http.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class RouteGroup {
    private String basePath;
    private List<Route> routes;

    public RouteGroup() {
        this.routes = new ArrayList<>();
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public RouteGroup connect(String path, BiConsumer<Request, Response> handler) {
        routes.add(new Route( Http.Method.CONNECT, basePath + path, handler));

        return this;
    }

    public RouteGroup delete(String path, BiConsumer<Request, Response> handler) {
        routes.add(new Route( Http.Method.DELETE, basePath + path, handler));

        return this;
    }

    public RouteGroup get(String path, BiConsumer<Request, Response> handler) {
        routes.add(new Route( Http.Method.GET, basePath + path, handler));

        return this;
    }

    public RouteGroup head(String path, BiConsumer<Request, Response> handler) {
        routes.add(new Route( Http.Method.HEAD, basePath + path, handler));

        return this;
    }

    public RouteGroup options(String path, BiConsumer<Request, Response> handler) {
        routes.add(new Route( Http.Method.OPTIONS, basePath + path, handler));

        return this;
    }

    public RouteGroup patch(String path, BiConsumer<Request, Response> handler) {
        routes.add(new Route( Http.Method.PATCH, basePath + path, handler));

        return this;
    }

    public RouteGroup post(String path, BiConsumer<Request, Response> handler) {
        routes.add(new Route( Http.Method.POST, basePath + path, handler));

        return this;
    }

    public RouteGroup put(String path, BiConsumer<Request, Response> handler) {
        routes.add(new Route( Http.Method.PUT, basePath + path, handler));

        return this;
    }

    public RouteGroup trace(String path, BiConsumer<Request, Response> handler) {
        routes.add(new Route( Http.Method.TRACE, basePath + path, handler));

        return this;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
