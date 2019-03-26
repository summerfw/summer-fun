package summer.fun;

import javax.swing.text.html.Option;
import java.util.Optional;

public class Configuration {
    private String host = "localhost";
    private int port = 8082;
    private String contextPath = "/";
    private ViewResolver viewResolver;
    public static final String STATIC_MAPPING = "/";
    public static final String WEBAPP_DIR = "src/main/webapp";

    private Configuration() {

    }

    private Configuration(Builder builder) {
        this.host = Optional.ofNullable(builder.host).orElse("localhost");
        this.port = Optional.ofNullable(builder.port).orElse(8082);
        this.contextPath = Optional.ofNullable(builder.contextPath).orElse("/");
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
