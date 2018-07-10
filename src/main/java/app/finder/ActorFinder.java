package app.finder;

import app.entity.Actor;
import app.exceptions.MyNotFoundException;
import app.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static app.DTO.ErrorCode.*;

@Component
public class ActorFinder {
    private final ActorRepository actorRepository;

    @Autowired
    public ActorFinder(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> findAlllActor()
    {
        return actorRepository.findAll();
    }

    public Actor findActorById(int id){
        return actorRepository.findById(id).orElseThrow(() -> new MyNotFoundException("Actor with given Id not found", DIFFERENT));
    }

    public List<Actor> findActorByFirstName(String name){
        return actorRepository.findAllByFirstName(name).orElseThrow(() -> new MyNotFoundException("Actor with given Id not found", DIFFERENT));
    }
}
