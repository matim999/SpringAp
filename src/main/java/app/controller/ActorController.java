package app.controller;

import app.DTO.ActorDto;
import app.DTO.BaseConverter;
import app.entity.Actor;
import app.finder.ActorFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/actor")
public class ActorController {
    private final ActorFinder actorFinder;
    private final BaseConverter<Actor, ActorDto> actorConverter;

    @Autowired
    public ActorController(ActorFinder actorFinder, BaseConverter<Actor, ActorDto> actorConverter) {
        this.actorFinder = actorFinder;
        this.actorConverter = actorConverter;
    }

    @GetMapping()
    public @ResponseBody
    ResponseEntity<List> findAllActor() {
        return new ResponseEntity(actorConverter.convertAll(actorFinder.findAlllActor()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<ActorDto> findByFirstName(@PathVariable int id) {
        return new ResponseEntity(actorConverter.convertAll(actorFinder.findActorById(id)), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    ResponseEntity<List> findByFirstName(@RequestParam String name) {
        return new ResponseEntity(actorConverter.convertAll(actorFinder.findActorByFirstName(name)), HttpStatus.OK);
    }
}
