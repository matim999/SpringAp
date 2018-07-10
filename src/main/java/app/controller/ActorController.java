package app.controller;

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

    @Autowired
    public ActorController(ActorFinder actorFinder) {
        this.actorFinder = actorFinder;
    }


    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<Actor> findByFirstName(@PathVariable int id) {
        return new ResponseEntity<>(actorFinder.findActorById(id), HttpStatus.OK);
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<List> findByFirstName(@RequestParam String name) {
        return new ResponseEntity<>(actorFinder.findActorByFirstName(name), HttpStatus.OK);
    }
}
