package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.ContextConfiguration;

@SpringBootApplication
@EnableScheduling
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
        SpringApplication.run(App.class, "AppConfig.java");
    }
}
