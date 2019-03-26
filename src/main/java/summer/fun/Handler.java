package summer.fun;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;
import summer.fun.http.HttpRequest;
import summer.fun.http.HttpResponse;
import summer.fun.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class Handler extends HttpHandler {
    private HttpRequest request;
    private HttpResponse response;
    private RouteCollection routeCollection;
    private ViewResolver viewResolver;

    @Override
    public void service(Request request, Response response) throws Exception {
        this.setRequest(request);
        this.setResponse(response);

        String currentHttpMethod = this.request.getMethod();
        String currentPath = this.request.getRequestUri();
        List<Route> routes = routeCollection.routes();
        Route currentRoute = routes.stream()
                .filter(route -> route.getHttpMethod().getName().equals(currentHttpMethod))
                .filter(route -> {
                    String newRoutePath = this.matchRoute(currentPath, route.getPath());
                    return (currentPath).equals(newRoutePath);
                })
                .findFirst().orElse(new Route(null, null, (req, res) -> {
                    Error error = new Error();
                    error.setTimestamp(LocalDateTime.now());
                    error.setStatus(HttpStatus.NOT_FOUND_404.getCode());
                    error.setError("Resource does not exist.");
                    error.setPath(currentPath);
                    res.status(HttpStatus.NOT_FOUND_404);
                    res.setContentType(ContentType.APPLICATION_JSON);
                    res.send(error);
                }));

        currentRoute.getHandler().accept(this.request, this.response);
    }

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        HttpRequest req = new HttpRequest();
        req.setRequest(request);
        this.request = req;
    }

    public HttpResponse getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        HttpResponse resp = new HttpResponse();
        resp.setResponse(response);
        resp.setViewResolver(this.viewResolver);
        this.response = resp;
        System.out.println(this.response.getViewResolver());
    }

    public RouteCollection getRouteCollection() {
        return routeCollection;
    }

    public void setRouteCollection(RouteCollection routeCollection) {
        this.routeCollection = routeCollection;
    }

    public void setViewResolver(ViewResolver viewResolver) {
        this.viewResolver = viewResolver;
    }

    public String matchRoute(String currentPath, String routePath) {
        if (currentPath.equals(routePath)) {
            return currentPath;
        } else {
            String[] currentPathSegments = currentPath.split("/");
            String[] routePathSegments = routePath.split("/");
            if (currentPathSegments.length != routePathSegments.length && (currentPathSegments.length - routePathSegments.length == 1) && currentPathSegments[1].equals(routePathSegments[0])) {
                String[] newRoutePathSegments = new String[routePathSegments.length];
                newRoutePathSegments[0] = currentPathSegments[0];
                for (int i = 1; i < currentPathSegments.length; i++) {
                    if (routePathSegments[i - 1].equals(currentPathSegments[i])) {
                        newRoutePathSegments[i] = currentPathSegments[i];
                    } else {
                        boolean hasCurlyBraces = routePathSegments[i].startsWith("{") && routePathSegments[i].endsWith("}") ? true : false;
                        if (hasCurlyBraces) {
                            newRoutePathSegments[i] = currentPathSegments[i];
                        } else {
                            newRoutePathSegments[i] = routePathSegments[i];
                        }
                    }
                }

                return String.join("/", newRoutePathSegments);

            } else if (currentPathSegments.length == routePathSegments.length) {
                String[] newRoutePathSegments = new String[routePathSegments.length];
                for (int i = 0; i < routePathSegments.length; i++) {
                    if (routePathSegments[i].equals(currentPathSegments[i])) {
                        newRoutePathSegments[i] = routePathSegments[i];
                    } else {
                        boolean hasCurlyBraces = routePathSegments[i].startsWith("{") && routePathSegments[i].endsWith("}") ? true : false;
                        if (hasCurlyBraces) {
                            newRoutePathSegments[i] = currentPathSegments[i];
                            String pathParamName = routePathSegments[i].substring(1, routePathSegments[i].length() - 1);
                            String pathParamValue = currentPathSegments[i];
                            this.request.setPathParam(pathParamName, pathParamValue);
                        } else {
                            newRoutePathSegments[i] = routePathSegments[i];
                        }
                    }
                }

                return String.join("/", newRoutePathSegments);

            } else {
                return routePath;
            }
        }
    }
}
