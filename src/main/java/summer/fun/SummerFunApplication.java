package summer.fun;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import summer.fun.http.Http;
import summer.fun.http.Request;
import summer.fun.http.Response;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SummerFunApplication {

    private Request request;
    private Response response;
    private RouteCollection routeCollection;
    private Optional<String> contextPath = Optional.of("/app");
    private Optional<String> host = Optional.of("localhost");
    private Optional<Integer> port = Optional.of(8080);
    private static final String STATIC_MAPPING = "/";
    private static final String SOURCES_MAIN = "src/main/";
    private Optional<String> staticRootDirectory = Optional.of("webapp");
    private static final String STATIC = "src/main/resurces/static";
    private String path;

    public SummerFunApplication() {
        this.routeCollection =  new RouteCollection();
    }

    public String getContextPath() {
        return contextPath.get();
    }

    public SummerFunApplication withContextPath(String contextPath) {
        if (contextPath.equals("/"))
            throw new IllegalArgumentException("Context Path " + contextPath + " is invalid.");
        this.contextPath = Optional.of(contextPath);

        return this;
    }

    public String getHost() {
        return this.host.get();
    }

    public SummerFunApplication withHost(String host) {
        this.host = Optional.of(host);

        return this;
    }

    public int getPort() {
        return this.port.get();
    }

    public SummerFunApplication withPort(int port) {
        this.port = Optional.of(port);

        return this;
    }

    public String getStaticRootDirectory() {
        return this.staticRootDirectory.get();
    }

    public SummerFunApplication withStaticRootDirectory(String directory) {
        if (directory.trim().startsWith("/"))
            directory = directory.substring(1);
        if (directory.trim().endsWith("/"))
            throw new IllegalArgumentException("Static directory " + directory + " is invalid. Remove '/' from end.");
        this.staticRootDirectory = Optional.of(directory);

        return this;
    }

    public void addRoute(Http.Method httpMethod, String path, BiConsumer<Request, Response> handler) {
        path = contextPath.get() + path;
        this.routeCollection.add(new Route(httpMethod, path, handler));
    }

    public <T> void addRoute(Http.Method httpMethod, String path, Consumer<? super T> handler) {
        path = contextPath.get() + path;
        this.routeCollection.add(new Route(httpMethod, path, handler));
    }

    public void addRoute(Route route) {
        this.routeCollection.add(route);
    }

    public void setRouteCollection(RouteCollection routeCollection) {
        this.routeCollection.addAll(routeCollection.routes());
    }

    public void group(String path, Supplier<RouteGroup> routeGroupFunctionSupplier) {
        List<Route> routeList = routeGroupFunctionSupplier.get().getRoutes();
        routeList.forEach(route -> this.addRoute(route));
    }

    public void group(String path, Function function) {
        function.apply();
    }

    public void get(String path, BiConsumer<Request, Response> handler) {
        this.addRoute(Http.Method.GET, path, handler);
    }

    public <T> void get(String path, Consumer<? super T> handler) {
        this.addRoute(Http.Method.GET, path, handler);
    }


    public void post(String path, BiConsumer<Request, Response> handler) {
        this.addRoute(Http.Method.POST, path, handler);
    }

    public void put(String path, BiConsumer<Request, Response> handler) {
        this.addRoute(Http.Method.PUT, path, handler);
    }

    public void delete(String path, BiConsumer<Request, Response> handler) {
        this.addRoute(Http.Method.DELETE, path, handler);
    }

    public void head(String path, BiConsumer<Request, Response> handler) {
        this.addRoute(Http.Method.HEAD, path, handler);
    }

    public void trace(String path, BiConsumer<Request, Response> handler) {
        this.addRoute(Http.Method.TRACE, path, handler);
    }

    public void connect(String path, BiConsumer<Request, Response> handler) {
        this.addRoute(Http.Method.CONNECT, path, handler);
    }

    public void options(String path, BiConsumer<Request, Response> handler) {
        this.addRoute(Http.Method.OPTIONS, path, handler);
    }

    public void any(String path, BiConsumer<Request, Response> handler) {
        this.addRoute(Http.Method.GET, path, handler);
        this.addRoute(Http.Method.POST, path, handler);
        this.addRoute(Http.Method.PUT, path, handler);
        this.addRoute(Http.Method.DELETE, path, handler);
        this.addRoute(Http.Method.HEAD, path, handler);
        this.addRoute(Http.Method.TRACE, path, handler);
        this.addRoute(Http.Method.CONNECT, path, handler);
        this.addRoute(Http.Method.OPTIONS, path, handler);
    }

    public void run(Function function) {
        final HttpServer server = new HttpServer();
        NetworkListener networkListener = new NetworkListener("summer-listener", this.host.get(), this.port.get());
        server.addListener(networkListener);
        StaticHttpHandler staticHttpHandler = new StaticHttpHandler(SOURCES_MAIN + staticRootDirectory.get());
        ServerConfiguration config = server.getServerConfiguration();
        config.addHttpHandler(staticHttpHandler, STATIC_MAPPING);
        SummerHandler summerHandler = new SummerHandler();
        summerHandler.setRouteCollection(routeCollection);
        config.addHttpHandler(summerHandler, contextPath.get());
        try {
            server.start();
            function.apply();
            System.in.read();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
