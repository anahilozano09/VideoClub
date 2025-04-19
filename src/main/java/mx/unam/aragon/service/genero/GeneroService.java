package mx.unam.aragon.service.genero;

import mx.unam.aragon.model.entity.GeneroEntity;

import java.util.List;

public interface GeneroService {
    GeneroEntity save(GeneroEntity genero);
    List<GeneroEntity> findAll();
    void deleteById(Long id);
    GeneroEntity findById(Long id);
}
