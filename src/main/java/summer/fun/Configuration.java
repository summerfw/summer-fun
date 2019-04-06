package summer.fun;

import java.util.Optional;
import summer.fun.util.Constants;
import summer.fun.util.StringPool;

public class Configuration {
    private String host = Constants.LOCALHOST;
    private int port = 8080;
    private String contextPath = StringPool.FORWARD_SLASH;
    private ViewResolver viewResolver;
    public static final String STATIC_MAPPING = StringPool.FORWARD_SLASH;
    public static final String WEBAPP_DIR = Constants.WEBAPP;

    private Configuration() {

    }

    private Configuration(Builder builder) {
        this.host = Optional.ofNullable(builder.host).orElse(Constants.LOCALHOST);
        this.port = Optional.ofNullable(builder.port).orElse(8080);
        this.contextPath = Optional.ofNullable(builder.contextPath).orElse(StringPool.FORWARD_SLASH);
        this.viewResolver = builder.viewResolver;
    }

    public static Configuration create() {
        return new Configuration();
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getContextPath() {
        return contextPath;
    }

    public ViewResolver getViewResolver() {
        return viewResolver;
    }

    public static class Builder {
        private String host;
        private Integer port;
        private String contextPath;
        private ViewResolver viewResolver;

        public Builder withHost(String host) {
            this.host = host;
            return this;
        }

        public Builder withPort(int port) {
            this.port = port;
            return this;
        }

        public Builder withContextPath(String contextPath) {
            this.contextPath = contextPath;
            return this;
        }

        public Builder withViewResolver(ViewResolver viewResolver) {
            this.viewResolver = viewResolver;
            return this;
        }

        public Configuration build() {
            return new Configuration(this);
        }
    }
}
