package app;

import app.DTO.responseDTO.InfoDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:lang/${language}.properties")
@PropertySource("classpath:application.properties")
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

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
