package app;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
public class App {
    @Value("${version.number}")
    private static String version;

    public static void main(String[] args) throws JsonProcessingException {
        ConfigurableApplicationContext context =
                SpringApplication.run(App.class, "AppConfig.java");

//
        MDC.put("VERSION", "1.0");
        Logger logger = LoggerFactory.getLogger(App.class.getName());
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
//        Marker marker = MarkerFactory.getMarker("TestMarker");
//        logger.info(marker, "{}; {} {}", value("action", "accepted order"),
//                keyValue("customer", customerId), keyValue("order", infoDto));
//        customerId = 137;
//        logger.info(marker, "{}; {} {}", value("action", "accepted order"),
//                keyValue("customer", customerId), keyValue("order", infoDto));
//        customerId = 139;
//        logger.info(marker, "{}; {} {}", value("action", "accepted order"),
//                keyValue("customer", customerId), keyValue("order", infoDto));
//        logger.info("{}; {} {}", value("action", "accepted order"),
//                keyValue("customer", customerId), keyValue("order", infoDto));
    }
}
