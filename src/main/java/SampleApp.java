import io.summer.fun.SummerFunApplication;

public class SampleApp {
    public static void main(String[] args) {
        SummerFunApplication app = new SummerFunApplication();

        app.get("/users", (request, response) -> {
            String users = "[{\"id\": 1, \"lastName\": \"Rizal\", \"firsName\": \"Jose\"}," +
                    "{\"id\": 2, \"lastName\": \"Bonifacio\", \"firsName\": \"Andres\"}," +
                    "{\"id\": 3, \"lastName\": \"Mabini\", \"firsName\": \"Apolinario\"}]";
            response.json(users);
        });

        app.get("/users/{id}", (request, response) -> {
            System.out.println(request.getPort());
            int id = Integer.parseInt(request.getPathParam("id"));
            if (id == 1) {
                String user = "{\"id\": 1, \"lastName\": \"Rizal\", \"firsName\": \"Jose\"}";
                response.json(user);
            } else {
                response.setStatus(404);
                String json = "{\"error\": {\"code\": 404, \"message\": \"Resource Not Found\"}}";
                response.json(json);
            }
        });

        app.run(() -> {
            System.out.println("Application is running on port " + app.getPort());
            System.out.println("Press any key to stop the server...");
        });
    }
}