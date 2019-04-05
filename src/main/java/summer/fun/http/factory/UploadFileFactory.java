package summer.fun.http.factory;

import summer.fun.http.message.Stream;
import summer.fun.http.message.UploadedFile;

@FunctionalInterface
public interface UploadFileFactory {
    // default error = UploadFileFactory.UPLOAD_ERR_OK.getValue(), clientFilename = '', clientMediaType = ''
    UploadedFile createUploadedFile(Stream stream, int size, int error, String clientFilename, String clientMediaType) throws IllegalArgumentException;
}
