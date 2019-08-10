package summer.fun.config;

import summer.fun.ViewResolver;

/**
 * 
 * @author julian
 */
public interface Configuration {    
    public static final String STATIC_MAPPING = "/";
    public static final String WEBAPP_DIR = "src/main/webapp";
    String getHost();
    int getPort();
    String getContextPath();
    ViewResolver getViewResolver();
}
