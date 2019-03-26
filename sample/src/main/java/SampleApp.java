import summer.fun.Configuration;
import summer.fun.PebbleViewResolver;
import summer.fun.SummerFun;
import summer.fun.ViewResolver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SampleApp {
    public static void main(String[] args) {
        ViewResolver viewResolver = new PebbleViewResolver();
        viewResolver.setPrefix("templates/");
        viewResolver.setSuffix(".html");
        Configuration config = Configuration.builder()
                .withContextPath("/api")
                .withViewResolver(viewResolver)
                .build();
        SummerFun app = new SummerFun()
                .withConfiguration(config);

        app.get("/heroes", (request, response) -> {
            List<Hero> heroes = getHeroes();
            response.json().send(heroes);
        });

        app.get("/hero-list", (request, response) -> {
            List<Hero> heroes = getHeroes();
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("heroes", heroes);
            response.render("hero/index", attributes);
        });

        app.run(() -> {
            System.out.println("Application is running on port " + config.getPort());
            System.out.println("Press any key to stop the server...");
        });
    }

    public static List<Hero> getHeroes() {
        Hero hero1 = new Hero();
        hero1.setId(1);
        hero1.setLastName("Rizal");
        hero1.setFirstName("Jose");

        Hero hero2 = new Hero();
        hero2.setId(2);
        hero2.setLastName("Bonifacio");
        hero2.setFirstName("Andres");

        return Arrays.asList(hero1, hero2);
    }

}
