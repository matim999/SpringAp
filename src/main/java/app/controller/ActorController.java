package app.controller;

import app.DTO.requestDTO.ActorDtoRequest;
import app.DTO.responseDTO.ActorDto;
import app.DTO.converter.BaseConverter;
import app.entity.Actor;
import app.finder.ActorFinder;
import app.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/actor")
public class ActorController {
    private final ActorFinder actorFinder;
    private final ActorService actorService;
    private final BaseConverter<Actor, ActorDto> actorConverter;

    @Autowired
    public ActorController(ActorFinder actorFinder, ActorService actorService, BaseConverter<Actor, ActorDto> actorConverter) {
        this.actorFinder = actorFinder;
        this.actorService = actorService;
        this.actorConverter = actorConverter;
    }

    @GetMapping()
    public @ResponseBody
    ResponseEntity<List> findAllActor() {
        return new ResponseEntity(actorConverter.convertAll(actorFinder.findAlllActor()), HttpStatus.OK);
    }

    @PostMapping()
    public @ResponseBody
    ResponseEntity addNewActor(@RequestBody ActorDtoRequest actorDtoRequest) {
        actorService.addNewActor(actorDtoRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<ActorDto> findByFirstName(@PathVariable int id) {
        return new ResponseEntity(actorConverter.convertAll(actorFinder.findActorById(id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseEntity deleteActorByID(@PathVariable int id) {
        actorService.deleteActorById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    ResponseEntity<List> findByFirstName(@RequestParam String name) {
        return new ResponseEntity(actorConverter.convertAll(actorFinder.findActorByFirstName(name)), HttpStatus.OK);
    }
}
