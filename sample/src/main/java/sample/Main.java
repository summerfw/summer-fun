package sample;

import sample.controller.UserController;
import sample.domain.User;
import sample.exception.Error;
import sample.exception.ErrorResponse;
import sample.repository.UserRepository;
import summer.fun.Configuration;
import summer.fun.SummerFun;
import summer.fun.http.HttpStatus;

public class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration.Builder()
                .host("localhost")
                .port(8080)
                .contextPath("/app")
                .build();

        SummerFun app = new SummerFun()
                .withPort(8082)
                .withContextPath("/api")
                .withConfig(config);

        UserController userController = new UserController();
        app.get("/users", userController::findAll);
        app.get("/users/{id}", userController::findById);

        app.run(() -> System.out.println("Server is running on " + app.getPort()));
    }
}
