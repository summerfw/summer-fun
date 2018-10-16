package io.summer.fun;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;

import java.util.List;

public class FrontHandler extends HttpHandler {

    private io.summer.fun.Request request;
    private io.summer.fun.Response response;
    private RouteCollection routeCollection;

    @Override
    public void service(Request request, Response response) throws Exception {
        this.setRequest(request);
        this.setResponse(response);

        String contextPath = this.request.getContextPath();
        String currentHttpMethod = this.request.getMethod();
        String currentPath = this.request.getRequestUri();
        List<Route> routes = routeCollection.routes();
        Route currentRoute = routes.stream()
                .filter(route -> route.getHttpMethod().equals(currentHttpMethod))
                .filter(route -> {
                    String newRoutePath = this.matchRoute(currentPath, contextPath + route.getPath());
                    return (currentPath).equals(newRoutePath);
                })
                .findFirst().orElse(new Route(null, null, (req, res) -> {
                    System.out.println("Error");
                    res.setStatus(404);
                    res.setContentType("text/html");
                    res.send("<h1>Error 404, page not found!</h1>");
                }));
        Handler handler = currentRoute.getHandler();
        handler.handle(this.request, this.response);
    }

    public io.summer.fun.Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        io.summer.fun.Request req = new io.summer.fun.Request();
        req.setRequest(request);
        this.request = req;
    }

    public io.summer.fun.Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        io.summer.fun.Response resp = new io.summer.fun.Response();
        resp.setResponse(response);
        this.response = resp;
    }

    public RouteCollection getRouteCollection() {
        return routeCollection;
    }

    public void setRouteCollection(RouteCollection routeCollection) {
        this.routeCollection = routeCollection;
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

    public void compileRoute() {

    }
}
