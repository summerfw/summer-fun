package summer.fun.http;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import summer.fun.ContentType;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Response
 */
public class Response {

    private org.glassfish.grizzly.http.server.Response response;
    private Jsonb jsonb;

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

    private void setStatus(int status) {
        this.response.setStatus(status);
    }

    public void status(Http.Status status) {
        this.response.setStatus(status.getCode());
    }

    public void send(Object object) {
        try {
            if (jsonb != null && response.getContentType().equals(ContentType.APPLICATION_JSON)) {
                String json = jsonb.toJson(object);
                this.getWriter().write(json);
            } else {
                this.getWriter().write(object.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void next() {

    }

    public Response json() {
        this.jsonb = JsonbBuilder.create();
        response.setContentType(ContentType.APPLICATION_JSON);

        return this;
    }

    public Response html() {
        response.setContentType(ContentType.TEXT_HTML);

        return this;
    }

    public Response text() {
        response.setContentType(ContentType.TEXT_PLAIN);

        return this;
    }

    public void render(String template, Map<String, Object> attributes) {
        try {
            PebbleEngine engine = new PebbleEngine.Builder().build();
            PebbleTemplate compiledTemplate = engine.getTemplate("templates/" + template + ".html");

            Writer writer = new StringWriter();
            if (attributes != null) {
                compiledTemplate.evaluate(writer, attributes);
            } else {
                compiledTemplate.evaluate(writer);
            }

            String output = writer.toString();
            html().send(output);
        } catch (IOException exception) {
            System.out.println("Template=" + template);
            System.out.println(attributes);
        }
    }

    public void render(String template) {
        this.render(template, null);
    }
}