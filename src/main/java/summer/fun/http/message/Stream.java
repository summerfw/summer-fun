package summer.fun.http.message;

import java.util.Map;

public interface Stream {
    String toString();
    void close();
    Object detach();
    int getSize();
    int tell() throws RuntimeException;
    boolean eof();
    boolean isSeekable();
    void seek(int offset, int whence) throws RuntimeException;
    void rewind() throws RuntimeException;
    boolean isWritable();
    int write(String string) throws RuntimeException;
    boolean isReadable();
    public String read(int length) throws RuntimeException;
    String getContents() throws RuntimeException;
    Map<String, String> getMetadata(String key);
    Map<String, String> getMetadata();
}
