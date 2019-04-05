package summer.fun.http.message;

public interface UploadedFile {
    Stream getStream() throws RuntimeException;
    void moveTo(String targetPath) throws IllegalArgumentException, RuntimeException;
    int getSize();
    int getError();
    String getClientFilename();
    String getClientMediaType();
}
