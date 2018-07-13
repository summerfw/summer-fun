package io.summerfun;

import io.summerfun.http.Request;
import io.summerfun.http.RequestImpl;
import io.summerfun.http.Response;
import io.summerfun.http.ResponseImpl;

public class Summer {
	
	public void get(String path, Handler handler) {		
		handler.handle(request(), response());
	}
	
	public void post(String path, Handler handler) {
		handler.handle(request(), response());
	}
	
	private Request request() {
		return new RequestImpl();
	}
	
	private Response response() {
		return new ResponseImpl();
	}
	
	public void run() {
		
	}
	
	public void run(int port, Message message) {
		message.log();
	}
}
