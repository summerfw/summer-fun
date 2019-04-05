package test;

import summer.fun.RequestHandler;
import summer.fun.SummerFun;
import summer.fun.http.message.Response;

public class App {
    public static void main(String[] args) {
        Router router = new Router();
        router.get("/hello", request -> {
            return null;
        });

        SummerFun app = new SummerFun();
    }
}
