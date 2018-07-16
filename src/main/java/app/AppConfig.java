package app;

import app.DTO.responseDTO.InfoDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:lang/${lang}.properties")
@PropertySource("classpath:application1.properties")
public class AppConfig {
    @Value("${profile}")
    public String profile;
    @Value("${title}")
    private String title;
    @Value("${description}")
    private String description;

    @Bean
    public InfoDto getInfoDto() {
        return new InfoDto(title, description, profile);
    }
}
