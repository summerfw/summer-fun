package summer.fun.http.message;

import summer.fun.http.HttpStatus;

public interface Response extends Message {
    int getStatusCode();
    Response withStatus(int code) throws IllegalArgumentException;
    Response withStatus(HttpStatus status) throws IllegalArgumentException;
    Response withStatus(int code, String reasonPhrase) throws IllegalArgumentException;
    String getReasonPhrase();
}
