package test;

import io.summer.fun.*;

public class App {

    public static void main(String[] args) {
        SummerFunApplication app = new SummerFunApplication();
//                .withContextPath("/app"); // Default is /
//                .withPort(8083);          // Default is 8080

        // 1.
        app.addRoute(HttpMethod.GET, "/api/v2/users", (request, response) -> {
            String users = "[{\"id\": 1, \"lastName\": \"Rizal\", \"firsName\": \"Jose\"}," +
                    "{\"id\": 2, \"lastName\": \"Bonifacio\", \"firsName\": \"Andres\"}," +
                    "{\"id\": 3, \"lastName\": \"Mabini\", \"firsName\": \"Apolinario\"}]";
            response.setStatus(200);
            response.json(users);
        });

        // 2.
        app.addRoute(new Route(HttpMethod.GET, "/api/v2/users/{id}", (request, response) -> {
            String user = "{\"id\": 1, \"lastName\": \"Rizal\", \"firsName\": \"Jose\"}";
            response.setStatus(200);
            response.json(user);
        }));

        // 3.
        RouteCollection routes = new RouteCollection();

        Route home = new Route(HttpMethod.GET, "/home", (request, response) -> {
            response.send("Welcome home!");
        });

        Route users = new Route(HttpMethod.GET, "/users", (request, response) -> {
            response.send(request.getQueryParam("name"));
        });

        routes.add(home);
        routes.add(users);
        app.setRouteCollection(routes);

        // 4.
        app.get("/contact", (request, response) -> {
            response.setStatus(200);
        });

        app.get("/error", (request, response) -> {
            System.out.println("Page not found!");
        });

        app.run((request, response) -> {
            System.out.println("Application is running on port ");
        });
    }

}
