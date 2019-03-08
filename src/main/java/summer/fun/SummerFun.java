package summer.fun;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import summer.fun.http.HttpMethod;
import summer.fun.http.HttpRequest;
import summer.fun.http.HttpResponse;

import java.util.Optional;
import java.util.function.BiConsumer;

public class SummerFun {

    private Configuration configuration;
    private HttpRequest request;
    private HttpResponse response;
    private RouteCollection routeCollection;
    private Optional<String> contextPath = Optional.of("/");
    private Optional<String> host = Optional.of("localhost");
    private Optional<Integer> port = Optional.of(8080);
    private static final String STATIC_MAPPING = "/";
    private static final String SOURCES_MAIN = "src/main/";
    private Optional<String> staticRootDirectory = Optional.of("webapp");
    private static final String STATIC = "src/main/resurces/static";
    private String path;

    public SummerFun() {
        this.routeCollection =  new RouteCollection();
    }

    public SummerFun withConfig(Configuration configuration) {
        this.configuration = configuration;

        return this;
    }

    public String getContextPath() {
        return contextPath.get();
    }

    public SummerFun withContextPath(String contextPath) {
        this.contextPath = Optional.of(contextPath);

        return this;
    }

    public String getHost() {
        return this.host.get();
    }

    public SummerFun withHost(String host) {
        this.host = Optional.of(host);

        return this;
    }

    public int getPort() {
        return this.port.get();
    }

    public SummerFun withPort(int port) {
        this.port = Optional.of(port);

        return this;
    }

    public String getStaticRootDirectory() {
        return this.staticRootDirectory.get();
    }

    public SummerFun withStaticRootDirectory(String directory) {
        if (directory.trim().startsWith("/"))
            directory = directory.substring(1);
        if (directory.trim().endsWith("/"))
            throw new IllegalArgumentException("Static directory " + directory + " is invalid. Remove trailing '/'.");
        this.staticRootDirectory = Optional.of(directory);

        return this;
    }

    public void addRoute(HttpMethod httpMethod, String path, BiConsumer<HttpRequest, HttpResponse> handler) {
        path = contextPath.get() + path;
        this.routeCollection.add(new Route(httpMethod, path, handler));
    }

    public void addRoute(Route route) {
        this.routeCollection.add(route);
    }

    public void setRouteCollection(RouteCollection routeCollection) {
        this.routeCollection.addAll(routeCollection.routes());
    }

    public void get(String path, BiConsumer<HttpRequest, HttpResponse> handler) {
        this.addRoute(HttpMethod.GET, path, handler);
    }

    public void post(String path, BiConsumer<HttpRequest, HttpResponse> handler) {
        this.addRoute(HttpMethod.POST, path, handler);
    }

    public void put(String path, BiConsumer<HttpRequest, HttpResponse> handler) {
        this.addRoute(HttpMethod.PUT, path, handler);
    }

    public void delete(String path, BiConsumer<HttpRequest, HttpResponse> handler) {
        this.addRoute(HttpMethod.DELETE, path, handler);
    }

    public void head(String path, BiConsumer<HttpRequest, HttpResponse> handler) {
        this.addRoute(HttpMethod.HEAD, path, handler);
    }

    public void trace(String path, BiConsumer<HttpRequest, HttpResponse> handler) {
        this.addRoute(HttpMethod.TRACE, path, handler);
    }

    public void connect(String path, BiConsumer<HttpRequest, HttpResponse> handler) {
        this.addRoute(HttpMethod.CONNECT, path, handler);
    }

    public void options(String path, BiConsumer<HttpRequest, HttpResponse> handler) {
        this.addRoute(HttpMethod.OPTIONS, path, handler);
    }

    public void any(String path, BiConsumer<HttpRequest, HttpResponse> handler) {
        this.addRoute(HttpMethod.GET, path, handler);
        this.addRoute(HttpMethod.POST, path, handler);
        this.addRoute(HttpMethod.PUT, path, handler);
        this.addRoute(HttpMethod.DELETE, path, handler);
        this.addRoute(HttpMethod.HEAD, path, handler);
        this.addRoute(HttpMethod.TRACE, path, handler);
        this.addRoute(HttpMethod.CONNECT, path, handler);
        this.addRoute(HttpMethod.OPTIONS, path, handler);
    }

    public void run(Runnable runnable) {
        final HttpServer server = new HttpServer();
        NetworkListener networkListener = new NetworkListener("summer-fun-listener", this.host.get(), this.port.get());
        server.addListener(networkListener);
        StaticHttpHandler staticHttpHandler = new StaticHttpHandler(SOURCES_MAIN + staticRootDirectory.get());
        ServerConfiguration config = server.getServerConfiguration();
        config.addHttpHandler(staticHttpHandler, STATIC_MAPPING);
        Handler handler = new Handler();
        handler.setRouteCollection(routeCollection);
        config.addHttpHandler(handler, contextPath.get());
        try {
            server.start();
            runnable.run();
            System.in.read();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
