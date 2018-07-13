package io.summerframework;

import io.summerframework.http.Request;
import io.summerframework.http.RequestImpl;
import io.summerframework.http.Response;
import io.summerframework.http.ResponseImpl;

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
