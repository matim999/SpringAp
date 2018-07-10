package app.service;

import app.DTO.InfoDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InfoService {
    @Value("${title}")
    String title;
    @Value("${description}")
    String description;
    @Value("${profile}")
    String profile;

    public InfoDto getInfo(){
        return new InfoDto(title, description, profile);
    }
}
