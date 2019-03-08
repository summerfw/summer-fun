package summer.fun;

import summer.fun.http.HttpRequest;
import summer.fun.http.HttpResponse;

import java.util.function.BiConsumer;

/**
 * Router
 */
public class Router {
    public void addRoute(String httpMethod, String path, BiConsumer<HttpRequest, HttpResponse> handler) {

    }
}