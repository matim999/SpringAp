package app;

import app.DTO.responseDTO.InfoDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication
//@EnableScheduling
//@EnableAutoConfiguration
public class App {
    public static void main(String[] args) throws JsonProcessingException {
//        ConfigurableApplicationContext context =
//                SpringApplication.run(App.class, "AppConfig.java");
        Logger logger = LoggerFactory.getLogger(App.class.getName());
        InfoDto infoDto = new InfoDto("gdgsdgdsg", "gdsgsgsd", "gdagdsgds");
        ObjectMapper mapper = new ObjectMapper();
        logger.info("message1 {} {}", mapper.writer().writeValueAsString(infoDto).replace("\\", ""), mapper.writeValueAsString(infoDto));
        logger.info("message2  " + String.valueOf(mapper.writeValueAsString(infoDto).toCharArray()));
        System.out.println(mapper.writeValueAsString(infoDto));
    }
}
