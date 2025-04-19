package mx.unam.aragon.service.director;

import mx.unam.aragon.model.entity.ActorEntity;
import mx.unam.aragon.model.entity.DirectorEntity;

import java.util.List;

public interface DirectorService {
    DirectorEntity save(DirectorEntity director);
    List<DirectorEntity> findAll();
    void deleteById(Long id);
    DirectorEntity findById(Long id);
}