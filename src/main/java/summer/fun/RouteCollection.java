package summer.fun;

import java.util.ArrayList;
import java.util.List;

/**
 * RouteCollection
 */
public class RouteCollection {
    private List<Route> routes = new ArrayList<>();

    public void add(Route route) {
        routes.add(route);
    }

    public void addAll(List<Route> routes) {
        this.routes.addAll(routes);
    }

    public List<Route> routes() {
        return routes;
    }
}