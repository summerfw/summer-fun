package io.summer.fun;

import org.glassfish.grizzly.http.server.HttpServer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class SummerFunApplication {

    private Request request;
    private Response response;
    private RouteCollection routeCollection;
    private Optional<String> contextPath = Optional.of("/");
    private Optional<Integer> port = Optional.of(8080);
    private String path;

    public SummerFunApplication() {
        this.routeCollection =  new RouteCollection();
    }

    public String getContextPath() {
        return contextPath.get();
    }

    public SummerFunApplication withContextPath(String contextPath) {
        this.contextPath = Optional.of(contextPath);

        return this;
    }

    public int getPort() {
        return this.port.get();
    }

    public SummerFunApplication withPort(int port) {
        this.port = Optional.of(port);

        return this;
    }

    public void addRoute(String httpMethod, String path, Handler handler) {
        this.routeCollection.add(new Route(httpMethod.toUpperCase(), path, handler));
    }

    public void addRoute(Route route) {
        route.getHttpMethod().toUpperCase();
        this.routeCollection.add(route);
    }

    public void setRouteCollection(RouteCollection routeCollection) {
        List<Route> routes = routeCollection.routes().stream()
                .map(route -> {
                    route.getHttpMethod().toUpperCase();
                    return route;
                })
                .collect(Collectors.toList());
        this.routeCollection.addAll(routes);
    }

    public void get(String path, Handler handler) {
        this.addRoute(HttpMethod.GET, path, handler);
    }

    public void post(String path, Handler handler) {
        this.addRoute(HttpMethod.POST, path, handler);
    }

    public void put(String path, Handler handler) {
        this.addRoute(HttpMethod.PUT, path, handler);
    }

    public void delete(String path, Handler handler) {
        this.addRoute(HttpMethod.DELETE, path, handler);
    }

    public void head(String path, Handler handler) {
        this.addRoute(HttpMethod.HEAD, path, handler);
    }

    public void trace(String path, Handler handler) {
        this.addRoute(HttpMethod.TRACE, path, handler);
    }

    public void connect(String path, Handler handler) {
        this.addRoute(HttpMethod.CONNECT, path, handler);
    }

    public void options(String path, Handler handler) {
        this.addRoute(HttpMethod.OPTIONS, path, handler);
    }

    public void run(Function function) {
        HttpServer server = HttpServer.createSimpleServer("/webapp", this.port.get());
        FrontHandler frontHandler = new FrontHandler();
        frontHandler.setRouteCollection(routeCollection);
        server.getServerConfiguration().addHttpHandler(frontHandler, contextPath.get());
        try {
            server.start();
            function.apply();
            System.in.read();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
