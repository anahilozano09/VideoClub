package mx.unam.aragon.repository;

import mx.unam.aragon.model.entity.PeliculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaRepository extends JpaRepository<PeliculaEntity,Long> {
}
