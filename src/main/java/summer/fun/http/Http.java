package summer.fun.http;

public final class Http {

    private Http() {
    }

    public enum Status {
        OK_200(200, "OK"),
        CREATED_201_(201, "CREATED"),
        NOT_FOUND_404(404, "NOT FOUND");

        private final int code;
        private final String reason;

        Status(int statusCode, String reasonPhrase) {
            this.code = statusCode;
            this.reason = reasonPhrase;
        }

        public int getCode() {
            return this.code;
        }

        public String getReason() {
            return this.reason;
        }

    }

    public enum Method {
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

        Method(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}
