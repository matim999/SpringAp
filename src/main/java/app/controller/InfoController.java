package app.controller;

import app.service.InfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/info")
public class InfoController {

    private final InfoService infoService;


    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<app.DTO.InfoDto> printInfo()
    {
        return new ResponseEntity<>(infoService.getInfo(), HttpStatus.OK);
    }
}
