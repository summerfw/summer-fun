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
 * HttpResponse
 */
public class HttpResponse {

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

    public void status(HttpStatus status) {
        this.response.setStatus(status.getCode());
    }

    public void send(Object object) {
        try {
            if (response.getContentType() != null &&
                    response.getContentType().equals(ContentType.APPLICATION_JSON) &&
                    !(object instanceof String)) {
                if (this.jsonb == null) {
                    this.jsonb = JsonbBuilder.create();
                }

                String json = this.jsonb.toJson(object);
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

    public HttpResponse json() {
        this.jsonb = JsonbBuilder.create();
        response.setContentType(ContentType.APPLICATION_JSON);

        return this;
    }

    public HttpResponse html() {
        response.setContentType(ContentType.TEXT_HTML);

        return this;
    }

    public HttpResponse text() {
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
            this.html().send(output);
        } catch (IOException exception) {
            System.out.println("Template=" + template);
            System.out.println(attributes);
        }
    }

    public void render(String template) {
        this.render(template, null);
    }
}