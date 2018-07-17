package app;

import app.DTO.responseDTO.InfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:lang/${lang}.properties")
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Value("${profile}")
    public String profile;
    @Value("${title}")
    private String title;
    @Value("${description}")
    private String description;
    @Autowired
    Environment env;

    @Bean
    public InfoDto getInfoDto() {
        return new InfoDto(title, description, profile);
    }
}
