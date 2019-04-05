package summer.fun.http.server;

import summer.fun.http.message.Response;
import summer.fun.http.message.ServerRequest;

@FunctionalInterface
public interface Middleware {
    Response process(ServerRequest request, RequestHandler handler);
}
