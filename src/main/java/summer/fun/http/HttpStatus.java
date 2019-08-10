package summer.fun.http;

/**
 * 
 * @author julian
 */
public enum HttpStatus {
    OK_200(200, "OK"),
    CREATED_201_(201, "CREATED"),
    NOT_FOUND_404(404, "NOT FOUND");

    private final int code;
    private final String reason;

    HttpStatus(int statusCode, String reasonPhrase) {
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
