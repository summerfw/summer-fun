package summer.fun;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;
import sample.controller.UserController;
import summer.fun.http.Http;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class SummerHandler extends HttpHandler {

    private summer.fun.http.Request request;
    private summer.fun.http.Response response;
    private RouteCollection routeCollection;

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
                .findFirst().orElse(new Route(null, null, (BiConsumer<summer.fun.http.Request, summer.fun.http.Response>)(req, res) -> {
                    res.status(Http.Status.NOT_FOUND_404);
                    res.setContentType(ContentType.TEXT_PLAIN);
                    res.send("Resource identified by path " + currentPath + ", does not exist.");
                }));
        Object handler;
        if (currentRoute.getHandler() instanceof BiConsumer) {
            handler = currentRoute.getHandler();
            ((BiConsumer) handler).accept(this.request, this.response);
        } else if (currentRoute.getHandler() instanceof Consumer) {

        }
    }

    public summer.fun.http.Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        summer.fun.http.Request req = new summer.fun.http.Request();
        req.setRequest(request);
        this.request = req;
    }

    public summer.fun.http.Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        summer.fun.http.Response resp = new summer.fun.http.Response();
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
