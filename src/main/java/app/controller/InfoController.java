package app.controller;

import app.DTO.responseDTO.InfoDto;
import app.entity.Role;
import app.entity.Rolee;
import app.entity.RolesList;
import app.service.InfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;

@RestController
public class InfoController {

    private final InfoService infoService;


    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping(path = "/info1")
    @PreAuthorize("hasAuthority('Role2')")
    public @ResponseBody
    ResponseEntity<InfoDto> printInfo1() {
        return new ResponseEntity<>(infoService.getInfo(), HttpStatus.OK);
    }

    @GetMapping(path = "/info2")
    @PreAuthorize("hasRole('Role2')")
    public @ResponseBody
    ResponseEntity<InfoDto> printInfo2() {
        return new ResponseEntity<>(infoService.getInfo(), HttpStatus.OK);
    }

//
//    @GetMapping(path = "/info4")
//    @PreAuthorize("hasRole('Role2')")
//    public @ResponseBody
//    ResponseEntity<InfoDto> printInfo4() {
//        return new ResponseEntity<>(infoService.getInfo(), HttpStatus.OK);
//    }

    @GetMapping(path = "/in")
    public @ResponseBody
    ResponseEntity<RolesList> printInfo3() {
        RestTemplate restTemplate = new RestTemplate();
        RolesList rolesList = restTemplate.getForObject("http://localhost:8083/authorities/1", RolesList.class);
        return new ResponseEntity<>(rolesList, HttpStatus.OK);
    }
}
