package summer.fun.http.factory;

import summer.fun.http.message.Response;

@FunctionalInterface
public interface ResponseFactory {
    Response createResponse(int code, String reasonPhrase); // default code = 200, default reasonPhrase = ''
}
