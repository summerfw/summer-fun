package summer.fun.http.factory;

import summer.fun.http.message.Request;
import summer.fun.http.message.Uri;

@FunctionalInterface
public interface RequestFactory {
    Request createRequest(String method, Uri uri);
}
