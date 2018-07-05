package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class App {
    public static void main (String [] args)
    {
        System.out.println();
        SpringApplication.run(App.class, args);
    }
}
