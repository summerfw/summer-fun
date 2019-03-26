package summer.fun;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import summer.fun.http.HttpMethod;
import summer.fun.http.HttpRequest;
import summer.fun.http.HttpResponse;

import java.util.function.BiConsumer;

public class SummerFun {

    private Configuration configuration;
    private RouteCollection routeCollection;


    public SummerFun() {
        this.configuration = Configuration.create();
        this.routeCollection =  new RouteCollection();
    }

    public SummerFun withConfiguration(Configuration configuration) {
        this.configuration = configuration;

        return this;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public RouteCollection getRouteCollection() {
        return routeCollection;
    }

    public void addRoute(HttpMethod httpMethod, String path, BiConsumer<HttpRequest, HttpResponse> handler) {
        String contextPath = this.configuration.getContextPath();
        if (!contextPath.equals("/")) path = contextPath + path;
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
        NetworkListener networkListener = new NetworkListener("summer-fun-listener", this.configuration.getHost(), this.configuration.getPort());
        server.addListener(networkListener);
        StaticHttpHandler staticHttpHandler = new StaticHttpHandler(Configuration.WEBAPP_DIR);
        ServerConfiguration config = server.getServerConfiguration();
        config.addHttpHandler(staticHttpHandler, Configuration.STATIC_MAPPING);
        Handler handler = new Handler();
        handler.setRouteCollection(routeCollection);
        handler.setViewResolver(this.configuration.getViewResolver());
        config.addHttpHandler(handler, this.configuration.getContextPath());
        try {
            server.start();
            runnable.run();
            System.in.read();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
