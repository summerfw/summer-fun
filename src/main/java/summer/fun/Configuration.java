package summer.fun;

import java.util.Optional;

public class Configuration {
    private String contextPath = "/";
    private String host = "localhost";
    private int port = 8082;
    public static final String STATIC_MAPPING = "/";
    public static final String WEBAPP_DIR = "src/main/webapp";

    private Configuration() {

    }

    private Configuration(Builder builder) {
        this.host = Optional.ofNullable(builder.host).orElse("localhost");
        this.port = Optional.ofNullable(builder.port).orElse(8082);
        this.contextPath = Optional.ofNullable(builder.contextPath).orElse("/");
    }

    public static Configuration create() {
        return new Configuration();
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getContextPath() {
        return contextPath;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public static class Builder {
        private String host;
        private Integer port;
        private String contextPath;

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

        public Configuration build() {
            return new Configuration(this);
        }
    }
}
