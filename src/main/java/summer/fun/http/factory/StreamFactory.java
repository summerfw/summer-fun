package summer.fun.http.factory;

import summer.fun.http.message.Stream;

public interface StreamFactory {
    Stream createStream(String content); // default content = ''
    Stream createStreamFromFile(String filename, String mode); // default mode = 'r'
    Stream createStreamFromResource(Object resource);
}
