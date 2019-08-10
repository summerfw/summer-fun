package summer.fun.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

/**
 * 
 * @author julian
 */
public class PropertiesUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtil.class);
    private static PropertiesUtil INSTANCE;
    private Properties properties;

    private static final String PROPERTIES_FILENAME = "summer.properties";

    private PropertiesUtil() {
        throw new AssertionError("Not allowed to instantiate.");
    }

    public static PropertiesUtil getInstance() {
        if (INSTANCE == null) INSTANCE = new PropertiesUtil();

        return INSTANCE;
    }

    public void read() throws IOException {
        properties= new Properties();

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILENAME);

            if (inputStream != null) {
                properties.load(inputStream);
            }
        } catch (IOException exception) {
            LOGGER.error("Property file '" + PROPERTIES_FILENAME + "' not found in the classpath");
        }
    }

    public Set<Object> getKeys() {
        return this.properties.keySet();
    }

    public String getValue(String key){
        return this.properties.getProperty(key);
    }
}
