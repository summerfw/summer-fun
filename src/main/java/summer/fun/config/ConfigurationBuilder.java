package summer.fun.config;

import summer.fun.ViewResolver;

/**
 * 
 * @author julian
 */
public class ConfigurationBuilder {
    private final ConfigurationProperties configurationProperties;

    private ConfigurationBuilder() {
        this.configurationProperties = new ConfigurationProperties();
    }

    public static ConfigurationBuilder newBuilder() {
        return new ConfigurationBuilder();
    }

    public ConfigurationBuilder withHost(String host) {
        this.configurationProperties.setHost(host);

        return this;
    }

    public ConfigurationBuilder withPort(int port) {
        this.configurationProperties.setPort(port);

        return this;
    }

    public ConfigurationBuilder withContextPath(String contextPath) {
        this.configurationProperties.setContextPath(contextPath);

        return this;
    }
    
    public ConfigurationBuilder withViewResolver(ViewResolver viewResolver) {
        this.configurationProperties.setViewResolver(viewResolver);
        
        return this;
    }

    public Configuration build() {
        return new ConfigurationImpl(this.configurationProperties);
    }

    private class ConfigurationProperties {
        private String host = "localhost";
        private int port = 7000;
        private String contextPath = "/";
        private ViewResolver viewResolver;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getContextPath() {
            return contextPath;
        }

        public void setContextPath(String contextPath) {
            this.contextPath = contextPath;
        }

        public ViewResolver getViewResolver() {
            return viewResolver;
        }

        public void setViewResolver(ViewResolver viewResolver) {
            this.viewResolver = viewResolver;
        }
    }

    private class ConfigurationImpl implements Configuration {
        private final String host;
        private final int port;
        private final String contextPath;
        private final ViewResolver viewResolver;

        ConfigurationImpl(ConfigurationProperties configurationProperties) {
            this.host = configurationProperties.getHost();
            this.port = configurationProperties.getPort();
            this.contextPath = configurationProperties.getContextPath();
            this.viewResolver = configurationProperties.getViewResolver();
        }

        @Override
        public String getHost() {
            return host;
        }

        @Override
        public int getPort() {
            return port;
        }

        @Override
        public String getContextPath() {
            return contextPath;
        }

        @Override
        public ViewResolver getViewResolver() {
            return viewResolver;
        }
    }

}
