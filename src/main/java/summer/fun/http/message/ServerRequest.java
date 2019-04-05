package summer.fun.http.message;

public interface ServerRequest {
    String[] getServerParams();
    String[] getCookieParams();
    ServerRequest withCookieParams(String[] cookies);
    String[] getQueryParams();
    ServerRequest withQueryParams(String[] query);
    UploadedFile[] getUploadedFiles();
    ServerRequest withUploadedFiles(Object[] $uploadedFiles) throws IllegalArgumentException;
    Object getParsedBody();
    ServerRequest withParsedBody(Object data) throws IllegalArgumentException;
    ServerRequest withParsedBody(Object[] data) throws IllegalArgumentException;
    Object[] getAttributes();
    Object getAttribute(String name); // mixed return value
    Object getAttribute(String name, Object defaultValue); // mixed return value; mixed defaultValue
    ServerRequest withAttribute(String name, Object value); // mixed value
    ServerRequest withoutAttribute(String name);
}
