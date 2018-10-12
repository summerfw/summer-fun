package io.summer.fun;

import org.glassfish.grizzly.http.server.HttpServer;

import java.util.Optional;

public class SummerFunApplication {

    private Request request;
    private Response response;
    private RouteCollection routeCollection;
    private Optional<String> contextPath = Optional.of("/");
    private Optional<Integer> port = Optional.of(8080);
    private String path;

    public SummerFunApplication() {
        this.request = new Request();
        this.response = new Response();
        this.routeCollection =  new RouteCollection();
    }

    public SummerFunApplication withContextPath(String contextPath) {
        this.contextPath = Optional.of(contextPath);

        return this;
    }

    public SummerFunApplication withPort(int port) {
        this.port = Optional.of(port);

        return this;
    }

    public SummerFunApplication route(String path) {
        this.path = path;

        return this;
    }

    public void get(String path, Handler handler) {
        this.addRoute(HttpMethod.GET, path, handler);
    }

    public void post(String path, Handler handler) {
        this.addRoute(HttpMethod.POST, path, handler);
    }

    public void addRoute(String httpMethod, String path, Handler handler) {
        this.routeCollection.add(new Route(httpMethod, path, handler));
    }

    public void addRoute(Route route) {
        this.routeCollection.add(route);
    }

    public void setRouteCollection(RouteCollection routeCollection) {
        if (this.routeCollection.routes().isEmpty())
            this.routeCollection = routeCollection;
        else {
            this.routeCollection.addAll(routeCollection.routes());
        }
    }

    public void run(Handler handler) {
        HttpServer server = HttpServer.createSimpleServer("/webapp", this.port.get());
        FrontHandler frontHandler = new FrontHandler();
        frontHandler.setRouteCollection(routeCollection);
        server.getServerConfiguration().addHttpHandler(frontHandler, contextPath.get());
        try {
            server.start();
            handler.handle(request, response);
            System.out.println("Press any key to stop the server...");
            System.in.read();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
