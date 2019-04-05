package summer.fun.http.message;

import java.util.Map;

public interface Message {
    String getProtocolVersion();
    Message withProtocolVersion(String version);
    Map<String, String> getHeaders();
    boolean hasHeader(String name);
    String[] getHeader(String name);
    String getHeaderLine(String name);
    Message withHeader(String name, String value) throws IllegalArgumentException;
    Message withHeader(String name, String[] value) throws IllegalArgumentException;
    Message withAddedHeader(String name, String value) throws IllegalArgumentException;
    Message withAddedHeader(String name, String[] value) throws IllegalArgumentException;
    Message withoutHeader(String name);
    Stream getBody();
    Message withBody(Stream body) throws IllegalArgumentException;
}
