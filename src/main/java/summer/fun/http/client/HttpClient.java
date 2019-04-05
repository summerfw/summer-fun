package summer.fun.http.client;

import summer.fun.http.message.Request;
import summer.fun.http.message.Response;

@FunctionalInterface
public interface HttpClient {
    Response send(Request request) throws ClientException;
}
