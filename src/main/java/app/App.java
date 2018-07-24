package app;

import app.DTO.responseDTO.InfoDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ContextIdApplicationContextInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

import static net.logstash.logback.argument.StructuredArguments.keyValue;
import static net.logstash.logback.argument.StructuredArguments.value;

@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
public class App {
    @Value("${version.number}")
    private static String version;

    public static void main(String[] args) throws JsonProcessingException {
        ConfigurableApplicationContext context =
                SpringApplication.run(App.class, "AppConfig.java");
        ContextIdApplicationContextInitializer contextInitializer = new ContextIdApplicationContextInitializer();

        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        System.out.println(context.getApplicationName());
//        System.out.println(context.);
//        contextInitializer.initialize(context);

//
//        MDC.put("VERSION", "gdgdsgs");
//        Logger logger = LoggerFactory.getLogger(App.class.getName());
//        InfoDto infoDto = new InfoDto("gdgsdgdsg", "gdsgsgsd", "gdagdsgds");
//        ObjectMapper mapper = new ObjectMapper();
//        String s1 = LoggingHelper.getJsonRepresentation(infoDto);
//        String s2 = s1.replaceAll("\\\\", "");
//        logger.info("message1{}", s1);
//        logger.info("message2  " + String.valueOf(mapper.writeValueAsString(infoDto).toCharArray()));
//        System.out.println(mapper.writeValueAsString(infoDto));
//        MDC.put("hasMessage", "true");
//        logger.info("{\"type\":\"example\",\"msg\":\"example of json message with type\"}");
//        int customerId = 132;
//        int orderId = 151;
//        logger.info("{}; {} {}", value("action", "accepted order"),
//                keyValue("customer", customerId), keyValue("order", infoDto));
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
        };
    }
}
