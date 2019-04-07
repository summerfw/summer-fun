import summer.fun.Configuration;
import summer.fun.PebbleViewResolver;
import summer.fun.SummerFun;
import java.util.HashMap;
import java.util.List;

public class SampleApp {
    public static void main(String[] args) {
        var viewResolver = new PebbleViewResolver()
             .setPrefix("templates")
             .setSuffix(".html");

        var config = Configuration.builder()
             .withContextPath("/app")
             .withViewResolver(viewResolver)
             .build();

        var app = new SummerFun()
             .withConfiguration(config);

        app.get("/heroes", (request, response) -> {
            var heroes = getHeroes();
            response.json().send(heroes);
        });

        app.get("/hero-list", (request, response) -> {
            var heroes = getHeroes();
            var attributes = new HashMap<String, Object>();
            attributes.put("heroes", heroes);
            response.render("hero/index", attributes);
        });

        app.run(() -> {
            System.out.println("Application is running on port " + config.getPort());
            System.out.println("Press any key to stop the server...");
        });
    }

    public static List<Hero> getHeroes() {
        var hero1 = new Hero();
        hero1.setId(1);
        hero1.setLastName("Rizal");
        hero1.setFirstName("Jose");

        var hero2 = new Hero();
        hero2.setId(2);
        hero2.setLastName("Bonifacio");
        hero2.setFirstName("Andres");

        return List.of(hero1, hero2);
    }

}
