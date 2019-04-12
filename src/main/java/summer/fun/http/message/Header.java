package summer.fun.http.message;

public interface Header {
    boolean isRequest();
    void getHttpHeader();
    Header withHttpHeader();
}
