package summer.fun;

import summer.fun.http.HttpRequest;
import summer.fun.http.HttpResponse;

@FunctionalInterface
public interface RequestHandler {
    void handle(HttpRequest request, HttpResponse response);
}
