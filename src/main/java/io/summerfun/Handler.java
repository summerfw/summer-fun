package io.summerfun;

import io.summerfun.http.Request;
import io.summerfun.http.Response;

@FunctionalInterface
public interface Handler {
	void handle(Request request, Response response);
}
