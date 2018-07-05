package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;

@SpringBootApplication
public class App {
    public static void main (String [] args)
    {
        System.out.println(HttpStatus.OK);
        SpringApplication.run(App.class, args);
    }
}
