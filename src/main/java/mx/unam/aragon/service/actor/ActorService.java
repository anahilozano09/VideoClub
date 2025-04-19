package mx.unam.aragon.service.actor;

import mx.unam.aragon.model.entity.ActorEntity;

import java.util.List;

public interface ActorService {
    ActorEntity save(ActorEntity actor);
    List<ActorEntity> findAll();
    void deleteById(Long id);
    ActorEntity findById(Long id);
}
