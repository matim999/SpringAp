package app.service;

import app.DTO.ErrorCode;
import app.DTO.converter.ActorConverter;
import app.DTO.converter.BaseConverter;
import app.DTO.converter.ToBaseConverter;
import app.DTO.requestDTO.ActorDtoRequest;
import app.DTO.responseDTO.ActorDto;
import app.entity.Actor;
import app.exceptions.ConflictException;
import app.exceptions.MyNotFoundException;
import app.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class ActorService {
    private final ActorRepository actorRepository;
    private final BaseConverter<Actor, ActorDto> actorConverter;
    private final ToBaseConverter<ActorDtoRequest, ActorDto> actorRequestConverter;

    @Autowired
    public ActorService(ActorRepository actorRepository, ActorConverter actorConverter, ToBaseConverter<ActorDtoRequest, ActorDto> actorRequestConverter) {
        this.actorRepository = actorRepository;
        this.actorConverter = actorConverter;
        this.actorRequestConverter = actorRequestConverter;
    }

    public void addNewActor(ActorDtoRequest actorDtoRequest)
    {
        Collection<Actor> collection = actorRepository.findAllByFirstNameAndLastName(actorDtoRequest.getFirstName(), actorDtoRequest.getLastName()).orElse(new ArrayList<>());
        if(collection.isEmpty()){
            actorRepository.save(new Actor(actorRequestConverter.convertAllToBase(actorDtoRequest)));
            return;
        }
        ActorDto actorDto = actorRequestConverter.convertAllToBase(actorDtoRequest);
        Collection<ActorDto> actor = actorConverter.convertAll(collection);
        actor.forEach(a -> {
            if (a.equals(actorDto))
                throw new ConflictException("Actor with given name nad surname already exists", ErrorCode.DIFFERENT);
        });
        actorRepository.save(new Actor(actorRequestConverter.convertAllToBase(actorDtoRequest)));
    }

    public void deleteActorById(int id)
    {
        actorRepository.findById(id).orElseThrow(() -> new MyNotFoundException("No Actor With Given Id", ErrorCode.DIFFERENT));
        actorRepository.deleteById(id);
    }
}
