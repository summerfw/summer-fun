package summer.fun;

import com.mitchellbosecke.pebble.PebbleEngine;
import java.io.File;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PebbleViewResolver implements ViewResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(PebbleViewResolver.class);
    private String prefix;
    private String suffix;

    @Override
    public ViewResolver setPrefix(String prefix) {
        this.prefix = prefix;

        return this;
    }

    @Override
    public ViewResolver setSuffix(String suffix) {
        this.suffix = suffix;

        return this;
    }

    @Override
    public Writer process(String template, Map<String, Object> attributes) {
        var engine = new PebbleEngine.Builder().build();
        var compiledTemplate = engine.getTemplate(this.prefix + File.separator + template + suffix);
        var writer = new StringWriter();
        try {
            if (attributes != null) {
                compiledTemplate.evaluate(writer, attributes);
            } else {
                compiledTemplate.evaluate(writer);
            }
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
        }

        return writer;
    }
}
