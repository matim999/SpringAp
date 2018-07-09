package app.service;

import app.DTO.InfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Service
public class InfoService {
    @Value("${title}")
    String title;
    @Value("${description}")
    String description;
    @Value("${profile}")
    String profile;
    private InfoDto infoDto;

    public InfoDto getInfo(){
        infoDto = new InfoDto(title, description, profile);
        return infoDto;
    }
}
