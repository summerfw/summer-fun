package summer.fun.http.message;

import summer.fun.http.HttpStatus;

public interface Response extends Message {
    int getStatus();
    void setStatus(int code) throws IllegalArgumentException;
    Response withStatus(int code) throws IllegalArgumentException;
    void setStatus(HttpStatus status) throws IllegalArgumentException;
    Response withStatus(HttpStatus status) throws IllegalArgumentException;
    void setStatus(int code, String reasonPhrase) throws IllegalArgumentException;
    Response withStatus(int code, String reasonPhrase) throws IllegalArgumentException;
    String getReasonPhrase();
    void setReasonPhrase(String reasonPhrase) throws IllegalArgumentException;
    Response withReasonPhrase(String reasonPhrase) throws IllegalArgumentException;
}
