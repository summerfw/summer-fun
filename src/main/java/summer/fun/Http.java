package summer.fun;

public final class Http {

    private Http() {
    }

    public enum Status {
        OK_200(200, "OK"),
        CREATED_201_(201, "CREATED");

        private final int code;
        private final String reason;

        Status(int statusCode, String reasonPhrase) {
            this.code = statusCode;
            this.reason = reasonPhrase;
        }

        public int code() {
            return this.code;
        }

        public String reason() {
            return this.reason;
        }

    }

    public enum Method {
        GET, POST
    }
}
