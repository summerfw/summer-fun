package summer.fun.http.server;

import summer.fun.http.factory.ServerRequest;
import summer.fun.http.message.Response;

@FunctionalInterface
public interface RequestHandler {
    Response handle(ServerRequest request);
}
