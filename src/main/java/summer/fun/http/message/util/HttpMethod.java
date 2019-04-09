package summer.fun.http.message.util;

public enum HttpMethod {
    CONNECT("CONNECT"),
    DELETE("DELETE"),
    GET("GET"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS"),
    PATCH("PATCH"),
    POST("POST"),
    PUT("PUT"),
    TRACE("TRACE");

    private final String name;

    HttpMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}