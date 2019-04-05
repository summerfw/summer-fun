package summer.fun.http.message;

import summer.fun.http.message.impl.HttpUri;

public interface Uri {
    String getScheme();
    String getAuthority();
    String getUserInfo();
    String getHost();
    int getPort();
    String getPath();
    String getQuery();
    String getFragment();
    Uri withScheme(String scheme) throws IllegalArgumentException;
    Uri withUserInfo(String user);
    Uri withUserInfo(String user, String password);
    Uri withHost(String host) throws IllegalArgumentException;
    Uri withPort(int port) throws IllegalArgumentException;
    Uri withPath(String path) throws IllegalArgumentException;
    Uri withQuery(String query) throws IllegalArgumentException;
    Uri withFragment(String fragment);
    String toString();
    static Uri of(String uri) {
        return new HttpUri().withScheme("");
    }
}
