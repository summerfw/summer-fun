package io.summerframework;

import io.summerframework.http.Request;
import io.summerframework.http.Response;

@FunctionalInterface
public interface Handler {
	void handle(Request request, Response response);
}
