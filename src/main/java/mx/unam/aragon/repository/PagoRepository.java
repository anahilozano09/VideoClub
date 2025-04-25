package mx.unam.aragon.repository;

import mx.unam.aragon.model.entity.PagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<PagoEntity, Long> {
}
