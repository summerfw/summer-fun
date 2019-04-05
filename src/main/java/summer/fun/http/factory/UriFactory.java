package summer.fun.http.factory;

import summer.fun.http.message.Uri;

@FunctionalInterface
public interface UriFactory {
    Uri createUri(String uri) throws IllegalArgumentException; // default uri = ''
}
