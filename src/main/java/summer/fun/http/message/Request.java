package summer.fun.http.message;

public interface Request extends Message {
    String getRequestTarget();
    Request withRequestTarget(Object requestTarget); // mixed requestTarget
    String getMethod();
    Request withMethod(String method) throws IllegalArgumentException;
    Uri getUri();
    Request withUri(Uri uri);
    Request withUri(Uri uri, boolean preserveHost);
}
