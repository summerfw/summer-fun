package summer.fun;

import java.util.ArrayList;
import java.util.List;
import summer.fun.http.HttpMethod;
import summer.fun.util.StringPool;

public class Router {
    private Configuration configuration;
    private List<Route> routes = new ArrayList<>();
    private String routesPath;

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public String getRoutesPath() {
        return routesPath;
    }

    public void setRoutesPath(Configuration configuration, String routesPath) {
        this.configuration = configuration;
        this.routesPath = routesPath;
    }
    
    public void addRoute(Configuration configuration, Route route) {
        this.configuration = configuration;
        var path = this.addContextPathToPath(route.getPath());
        route.setPath(path);
        this.routes.add(route);
    }
    
    public Router get(RequestHandler handler) {
        var path = this.addContextPathToPath(this.routesPath);
        this.routes.add(new Route(HttpMethod.GET, path, handler));
        
        return this;
    }

    public Router post(RequestHandler handler) {     
        var path = this.addContextPathToPath(this.routesPath);   
        this.routes.add(new Route(HttpMethod.POST, path, handler));
        
        return this;
    }

    public Router put(RequestHandler handler) {   
        var path = this.addContextPathToPath(this.routesPath);
        this.routes.add(new Route(HttpMethod.PUT, path, handler));
        
        return this;
    }

    public Router delete(RequestHandler handler) { 
        var path = this.addContextPathToPath(this.routesPath); 
        this.routes.add(new Route(HttpMethod.DELETE, path, handler));
        
        return this;
    }

    public Router head(RequestHandler handler) {  
        var path = this.addContextPathToPath(this.routesPath);      
        this.routes.add(new Route(HttpMethod.HEAD, path, handler));
        
        return this;
    }

    public Router trace(RequestHandler handler) {   
        var path = this.addContextPathToPath(this.routesPath);     
        this.routes.add(new Route(HttpMethod.TRACE, path, handler));
        
        return this;
    }

    public Router connect(RequestHandler handler) { 
        var path = this.addContextPathToPath(this.routesPath);       
        this.routes.add(new Route(HttpMethod.CONNECT, path, handler));
        
        return this;
    }

    public Router options(RequestHandler handler) { 
        var path = this.addContextPathToPath(this.routesPath);       
        this.routes.add(new Route(HttpMethod.OPTIONS, path, handler));
        
        return this;
    }
    
    private String addContextPathToPath(String path) {
        var contextPath = this.configuration.getContextPath();
        if (!contextPath.equals(StringPool.FORWARD_SLASH)) {
            return contextPath + path;
        }
        
        return path;
    }
}
