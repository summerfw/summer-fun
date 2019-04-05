package summer.fun.http.message.impl;

import summer.fun.http.message.Uri;

public class HttpUri implements Uri {
    private String scheme;
    private String authority;
    private String user;
    private String password;
    private String host;
    private int port;
    private String path;
    private String query;
    private String fragment;

    @Override
    public String getScheme() {
        return scheme;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    @Override
    public String getUserInfo() {
        return user;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public String getQuery() {
        return query;
    }

    @Override
    public String getFragment() {
        return fragment;
    }

    @Override
    public Uri withScheme(String scheme) throws IllegalArgumentException {
        this.scheme = scheme;
        return this;
    }

    @Override
    public Uri withUserInfo(String user) {
        this.user = user;
        return this;
    }

    @Override
    public Uri withUserInfo(String user, String password) {
        this.user = user;
        return this;
    }

    @Override
    public Uri withHost(String host) throws IllegalArgumentException {
        this.host = host;
        return this;
    }

    @Override
    public Uri withPort(int port) throws IllegalArgumentException {
        this.port = port;
        return this;
    }

    @Override
    public Uri withPath(String path) throws IllegalArgumentException {
        this.path = path;
        return this;
    }

    @Override
    public Uri withQuery(String query) throws IllegalArgumentException {
        this.query = query;
        return this;
    }

    @Override
    public Uri withFragment(String fragment) {
        this.fragment = fragment;
        return this;
    }
}
