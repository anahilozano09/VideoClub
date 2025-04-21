package mx.unam.aragon.repository;

import mx.unam.aragon.model.entity.ejemplar.EjemplarEntity;
import mx.unam.aragon.model.entity.ejemplar.EjemplarId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjemplarRepository extends JpaRepository<EjemplarEntity, EjemplarId> {
}
