package mx.unam.aragon.repository;

import mx.unam.aragon.model.entity.SocioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocioRepository extends JpaRepository<SocioEntity, Long> {
}
