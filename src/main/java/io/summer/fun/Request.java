package io.summer.fun;

import java.util.HashMap;
import java.util.Map;

/**
 * Request
 */
public class Request {

    private org.glassfish.grizzly.http.server.Request request;
    private Map<String, String> pathParams = new HashMap<>();

    public org.glassfish.grizzly.http.server.Request getRequest() {
        return request;
    }

    public void setRequest(org.glassfish.grizzly.http.server.Request request) {
        this.request = request;
    }

    public String getContextPath() {
        return this.getRequest().getContextPath();
    }

    public int getPort() {
        return this.request.getServerPort();
    }

    public void setPort(int port) {
        this.request.setServerPort(port);
    }

    public String getMethod() {
        return this.request.getMethod().toString();
    }

    public String getRequestUri() {
        return this.request.getRequestURI();
    }

    public String getParam(String parameter) {
        return this.request.getParameter(parameter);
    }

    public String getPathParam(String parameter) {
        return this.pathParams.get(parameter);
    }

    public void setPathParam(String parameter, String value) {
        this.pathParams.put(parameter, value);
    }

    public Map<String, String> getPathParams() {
        return this.pathParams;
    }

}