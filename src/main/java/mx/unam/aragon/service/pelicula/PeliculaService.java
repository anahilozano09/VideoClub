package mx.unam.aragon.service.pelicula;

import mx.unam.aragon.model.entity.PeliculaEntity;

import java.util.List;

public interface PeliculaService {
    PeliculaEntity save(PeliculaEntity pelicula);
    List<PeliculaEntity> findAll();
    void deleteById(Long id);
    PeliculaEntity findById(Long id);
}
