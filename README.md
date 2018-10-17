# Summer Fun
Summer Fun is a microframework for Java.

```java
public class SampleApp {
    public static void main(String[] args) {
        SummerFunApplication app = new SummerFunApplication();

        app.get("/users", (request, response) -> {
            String users = "[{\"id\": 1, \"lastName\": \"Rizal\", \"firsName\": \"Jose\"}," +
                "{\"id\": 2, \"lastName\": \"Bonifacio\", \"firsName\": \"Andres\"}," +
                "{\"id\": 3, \"lastName\": \"Mabini\", \"firsName\": \"Apolinario\"}]";
             response.json(users);
        });

        app.run(() -> {
            System.out.println("Application is running on port " + app.getPort());
            System.out.println("Press any key to stop the server...");
        });
    }
}
```

# Features
* Inspired by microframeworks from different programming languages - [Slim Framework](https://www.slimframework.com), [ExpressJS](https://expressjs.com), [Spark](http://sparkjava.com).
* Uses Grizzly HTTP Server framework, a component of [Grizzly](https://javaee.github.io/grizzly) NIO framework which has been designed to help developers to take advantage of the Java™ NIO API.
* Lightweight

# Getting started
Clone the repository and install using Maven:
```
git clone https://github.com/julianjupiter/summer-fun
cd summer-fun
mvn install
```

Clone, build, and run sample application:
```
git clone https://github.com/julianjupiter/summer-fun-sample
cd summer-fun-sample
mvn package
java -jar ./target/summer-fun-sample-1.0.0.jar
```

Open browser:
```
http://localhost:8083/app/users
http://localhost:8083/app/users/1
http://localhost:8083/app/users/2
http://localhost:8083/app/users/3
http://localhost:8083/app/home
http://localhost:8083/app/hello
http://localhost:8083/app/contact
```

# Disclaimer
This is not ready fo production yet. Any suggestions and help would be highly appreciated.

# License
Summer Fun, a Java microframework.  
Copyright (C) 2018  Julian V. Jupiter

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
