package summer.fun;

import java.io.IOException;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import summer.fun.http.HttpMethod;

public class SummerFun {
    private static final Logger LOGGER = LoggerFactory.getLogger(SummerFun.class);
    private Configuration configuration;
    private final Router router;

    public SummerFun() {
        this.configuration = Configuration.create();
        this.router = new Router();
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
    
    public Router routes(String path) {
        this.router.setRoutesPath(this.configuration, path);
        
        return this.router;
    }

    public void get(String path, RequestHandler handler) {
        this.router.addRoute(this.configuration, new Route(HttpMethod.GET, path, handler));
    }

    public void post(String path, RequestHandler handler) {
        this.router.addRoute(this.configuration, new Route(HttpMethod.POST, path, handler));
    }

    public void put(String path, RequestHandler handler) {
        this.router.addRoute(this.configuration, new Route(HttpMethod.PUT, path, handler));
    }

    public void delete(String path, RequestHandler handler) {
        var route = new Route(HttpMethod.DELETE, path, handler);
        this.router.addRoute(this.configuration, route);
    }

    public void head(String path, RequestHandler handler) {
        this.router.addRoute(this.configuration, new Route(HttpMethod.HEAD, path, handler));
    }

    public void trace(String path, RequestHandler handler) {
        this.router.addRoute(this.configuration, new Route(HttpMethod.TRACE, path, handler));
    }

    public void connect(String path, RequestHandler handler) {
        this.router.addRoute(this.configuration, new Route(HttpMethod.CONNECT, path, handler));
    }

    public void options(String path, RequestHandler handler) {
        this.router.addRoute(this.configuration, new Route(HttpMethod.OPTIONS, path, handler));
    }

    public void any(String path, RequestHandler handler) {
        this.router.addRoute(this.configuration, new Route(HttpMethod.GET, path, handler));
        this.router.addRoute(this.configuration, new Route(HttpMethod.POST, path, handler));
        this.router.addRoute(this.configuration, new Route(HttpMethod.PUT, path, handler));
        this.router.addRoute(this.configuration, new Route(HttpMethod.DELETE, path, handler));
        this.router.addRoute(this.configuration, new Route(HttpMethod.HEAD, path, handler));
        this.router.addRoute(this.configuration, new Route(HttpMethod.TRACE, path, handler));
        this.router.addRoute(this.configuration, new Route(HttpMethod.CONNECT, path, handler));
        this.router.addRoute(this.configuration, new Route(HttpMethod.OPTIONS, path, handler));
    }

    public void run(Runnable runnable) {
        final var server = new HttpServer();
        var networkListener = new NetworkListener("summer-fun-listener", this.configuration.getHost(), this.configuration.getPort());
        server.addListener(networkListener);
        var staticHttpHandler = new StaticHttpHandler(Configuration.WEBAPP_DIR);
        var config = server.getServerConfiguration();
        config.addHttpHandler(staticHttpHandler, Configuration.STATIC_MAPPING);
        var httpHandler = new HttpHandler();
        httpHandler.setRoutes(this.router.getRoutes());
        httpHandler.setViewResolver(this.configuration.getViewResolver());
        config.addHttpHandler(httpHandler, this.configuration.getContextPath());
        try {
            server.start();
            runnable.run();
            System.in.read();
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
        }
    }
}
