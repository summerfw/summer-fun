package summer.fun;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import summer.fun.config.Configuration;

/**
 * 
 * @author julian
 */
public class RouteCollection {
    private final List<Route> routes = new ArrayList<>();

    public void add(Route route, Configuration configuration) {
        String contextPath = configuration.getContextPath();
        if (!contextPath.equals("/")) {
            String path = contextPath + route.getPath();
            route.setPath(path);
        }

        routes.add(route);
    }

    public void addAll(List<Route> routes, Configuration configuration) {
        String contextPath = configuration.getContextPath();
        if (!contextPath.equals("/")) {
            List<Route> routeList = routes.stream()
                    .map(route -> {
                        String path = contextPath + route.getPath();
                        route.setPath(path);
                        return route;
                    })
                    .collect(Collectors.toList());
            this.routes.addAll(routeList);
        } else {
            this.routes.addAll(routes);
        }
    }

    public List<Route> routes() {
        return routes;
    }
}