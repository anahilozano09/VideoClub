package mx.unam.aragon.repository;

import mx.unam.aragon.model.entity.ActorEntity;
import mx.unam.aragon.model.entity.DirectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<DirectorEntity,Long> {
}
