package summer.fun;

import java.io.IOException;
import java.io.Writer;

/**
 * Response
 */
public class Response {

    private org.glassfish.grizzly.http.server.Response response;

    public org.glassfish.grizzly.http.server.Response getResponse() {
        return response;
    }

    public void setResponse(org.glassfish.grizzly.http.server.Response response) {
        this.response = response;
    }

    public Writer getWriter() {
        return this.response.getWriter();
    }

    public String getContentType() {
        return this.response.getContentType();
    }

    public void setContentType(String contentType) {
        this.response.setContentType(contentType);
    }

    public int getStatus() {
        return this.response.getStatus();
    }

    public void setStatus(int status) {
        this.response.setStatus(status);
    }

    public void send(String content) {
        try {
            this.getWriter().write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void json(String content) {
        try {
            this.response.setContentType(ContentType.APPLICATION_JSON);
            this.getWriter().write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void html(String content) {
        try {
            this.response.setContentType(ContentType.TEXT_HTML);
            this.getWriter().write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void text(String content) {
        try {
            this.response.setContentType(ContentType.TEXT_PLAIN);
            this.getWriter().write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}