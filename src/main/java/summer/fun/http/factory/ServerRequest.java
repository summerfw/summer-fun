package summer.fun.http.factory;

import summer.fun.http.message.Uri;

@FunctionalInterface
public interface ServerRequest {
    ServerRequest createServerRequest(String method, Uri uri, Object[] serverParams);
}
