package app.controller;

import app.DTO.responseDTO.InfoDto;
import app.service.InfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/info")
public class InfoController {

    private final InfoService infoService;


    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<InfoDto> printInfo() {
        return new ResponseEntity<>(infoService.getInfo(), HttpStatus.OK);
    }
}
