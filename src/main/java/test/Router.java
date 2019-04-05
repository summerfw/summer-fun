package test;

import summer.fun.http.server.RequestHandler;

import java.util.ArrayList;
import java.util.List;

public class Router {
    private List<Route> routes = new ArrayList<>();

    public void get(String path, RequestHandler handler) {
        Route route = new Route();
        this.routes.add(route);
    }
}
