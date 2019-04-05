package devtest;

import summer.fun.http.client.HttpClient;
import summer.fun.http.client.impl.SummerHttpClient;
import summer.fun.http.message.Request;
import summer.fun.http.message.Uri;
import summer.fun.http.message.impl.HttpRequest;

import java.net.URI;

public class App {
    public static void main(String[] args) {
        HttpClient httpClient = SummerHttpClient.newBuilder()
                .get(() -> URI.create("http://localhost:8080/api/users"))
                .build();
        Request request = new HttpRequest()
                .withUri(Uri.of("http://localhost:8080/api/users"));
        //httpClient.send()
        Request request1 = HttpRequest.newBuilder()
                .withMethod("GET")
                .withRequestTarget("http://localhost:8080/api/users")
                .build();
    }
}
