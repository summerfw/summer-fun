package summer.fun;

import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;
import summer.fun.http.HttpRequest;
import summer.fun.http.HttpResponse;
import summer.fun.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import summer.fun.util.StringPool;

public class HttpHandler extends org.glassfish.grizzly.http.server.HttpHandler {
    private HttpRequest request;
    private HttpResponse response;
    private List<Route> routes;
    private ViewResolver viewResolver;

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        var req = new HttpRequest();
        req.setRequest(request);
        this.request = req;
    }

    public HttpResponse getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        var resp = new HttpResponse();
        resp.setResponse(response);
        resp.setViewResolver(this.viewResolver);
        this.response = resp;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public void setViewResolver(ViewResolver viewResolver) {
        this.viewResolver = viewResolver;
    }

    @Override
    public void service(Request request, Response response) throws Exception {
        this.setRequest(request);
        this.setResponse(response);

        var currentHttpMethod = this.request.getMethod();
        var currentPath = this.request.getRequestUri();
        var currentRoute = routes.stream()
                .filter(route -> route.getHttpMethod().getName().equals(currentHttpMethod))
                .filter(route -> {
                    var newRoutePath = this.matchRoute(currentPath, route.getPath());
                    return (currentPath).equals(newRoutePath);
                })
                .findFirst().orElse(new Route(null, null, (req, res) -> {
                    var error = new Error();
                    error.setTimestamp(LocalDateTime.now());
                    error.setStatus(HttpStatus.NOT_FOUND_404.getCode());
                    error.setError("Resource does not exist.");
                    error.setPath(currentPath);
                    res.setStatus(HttpStatus.NOT_FOUND_404.getCode());
                    res.setContentType(ContentType.APPLICATION_JSON);
                    res.send(error);
                }));

        currentRoute.getHandler().handle(this.request, this.response);
    }

    public String matchRoute(String currentPath, String routePath) {
        if (currentPath.equals(routePath)) {
            return currentPath;
        } else {
            String[] currentPathSegments = currentPath.split(StringPool.FORWARD_SLASH);
            String[] routePathSegments = routePath.split(StringPool.FORWARD_SLASH);
            if (currentPathSegments.length != routePathSegments.length && (currentPathSegments.length - routePathSegments.length == 1) && currentPathSegments[1].equals(routePathSegments[0])) {
                String[] newRoutePathSegments = new String[routePathSegments.length];
                newRoutePathSegments[0] = currentPathSegments[0];
                for (int i = 1; i < currentPathSegments.length; i++) {
                    if (routePathSegments[i - 1].equals(currentPathSegments[i])) {
                        newRoutePathSegments[i] = currentPathSegments[i];
                    } else {
                        boolean hasCurlyBraces = routePathSegments[i].startsWith(StringPool.OPEN_CURLY_BRACE) && routePathSegments[i].endsWith(StringPool.CLOSE_CURLY_BRACE);
                        if (hasCurlyBraces) {
                            newRoutePathSegments[i] = currentPathSegments[i];
                        } else {
                            newRoutePathSegments[i] = routePathSegments[i];
                        }
                    }
                }

                return String.join(StringPool.FORWARD_SLASH, newRoutePathSegments);

            } else if (currentPathSegments.length == routePathSegments.length) {
                String[] newRoutePathSegments = new String[routePathSegments.length];
                for (int i = 0; i < routePathSegments.length; i++) {
                    if (routePathSegments[i].equals(currentPathSegments[i])) {
                        newRoutePathSegments[i] = routePathSegments[i];
                    } else {
                        boolean hasCurlyBraces = routePathSegments[i].startsWith(StringPool.OPEN_CURLY_BRACE) && routePathSegments[i].endsWith(StringPool.CLOSE_CURLY_BRACE);
                        if (hasCurlyBraces) {
                            newRoutePathSegments[i] = currentPathSegments[i];
                            var pathParamName = routePathSegments[i].substring(1, routePathSegments[i].length() - 1);
                            var pathParamValue = currentPathSegments[i];
                            this.request.setPathParam(pathParamName, pathParamValue);
                        } else {
                            newRoutePathSegments[i] = routePathSegments[i];
                        }
                    }
                }

                return String.join(StringPool.FORWARD_SLASH, newRoutePathSegments);

            } else {
                return routePath;
            }
        }
    }
}
