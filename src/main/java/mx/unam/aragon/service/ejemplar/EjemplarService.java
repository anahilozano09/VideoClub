package mx.unam.aragon.service.ejemplar;

import mx.unam.aragon.model.entity.PeliculaEntity;
import mx.unam.aragon.model.entity.ejemplar.EjemplarEntity;
import mx.unam.aragon.model.entity.ejemplar.EjemplarId;

import java.util.List;

public interface EjemplarService {
    EjemplarEntity save(EjemplarEntity ejemplar);
    List<EjemplarEntity> findAll();
    void deleteById(EjemplarId id);
    EjemplarEntity findById(EjemplarId id);
    boolean existsById(EjemplarId id);
    List<EjemplarEntity> findByPelicula(PeliculaEntity pelicula);
}
