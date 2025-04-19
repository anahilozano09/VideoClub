package mx.unam.aragon.repository;

import mx.unam.aragon.model.entity.participa.ParticipaEntity;
import mx.unam.aragon.model.entity.participa.ParticipaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipaRepository extends JpaRepository<ParticipaEntity, ParticipaId> {
}
