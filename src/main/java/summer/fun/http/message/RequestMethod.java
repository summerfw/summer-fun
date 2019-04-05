package summer.fun.http.message;

public enum RequestMethod {
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

    RequestMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}