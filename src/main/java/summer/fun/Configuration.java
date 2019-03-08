package summer.fun;

public class Configuration {
    private String host = "localhost";
    private int port = 8080;
    private String contextPath = "/";

    private Configuration(Builder builder) {
        this.host = builder.host;
        this.port = builder.port;
        this.contextPath = builder.contextPath;
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

    public static class Builder {
        private String host;
        private int port;
        private String contextPath;

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public Builder contextPath(String contextPath) {
            this.contextPath = contextPath;
            return this;
        }

        public Configuration build() {
            return new Configuration(this);
        }
    }
}
