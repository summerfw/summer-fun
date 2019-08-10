package summer.fun;

import java.io.Writer;
import java.util.Map;

/**
 * 
 * @author julian
 */
public interface ViewResolver {
    ViewResolver setPrefix(String prefix);
    ViewResolver setSuffix(String suffix);
    Writer process(String template, Map<String, Object> attributes);
}
