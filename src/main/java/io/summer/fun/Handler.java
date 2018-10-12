package io.summer.fun;

/**
 * Handler
 */
@FunctionalInterface
public interface Handler {

    void handle(Request request, Response response);

}