package sample.controller;

import sample.domain.User;
import sample.exception.Error;
import sample.exception.ErrorResponse;
import sample.repository.UserRepository;
import summer.fun.http.HttpRequest;
import summer.fun.http.HttpResponse;
import summer.fun.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class UserController {
    private UserRepository userRepository;

    public UserController() {
        this.userRepository = new UserRepository();
    }

    public void findAll(HttpRequest request, HttpResponse response) {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("users", this.userRepository.findAll());

        response.render("user/index", attributes);
    }

    public void findById(HttpRequest request, HttpResponse response) {
        int id = Integer.parseInt(request.getPathParam("id"));
        try {
            User user = this.userRepository.findById(id)
                    .orElseThrow(() -> new Exception("No user with ID " + id + " found."));
            response.json().send(user);
        } catch (Exception exception) {
            ErrorResponse errorResponse = new ErrorResponse();
            Error error = new Error();
            error.setStatus(HttpStatus.NOT_FOUND_404.getCode());
            error.setMessage(exception.getMessage());
            errorResponse.setError(error);
            response.status(HttpStatus.NOT_FOUND_404);
            response.json().send(errorResponse);
        }
    }
}
