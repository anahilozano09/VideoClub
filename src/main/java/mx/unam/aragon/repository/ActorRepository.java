package mx.unam.aragon.repository;

import mx.unam.aragon.model.entity.ActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<ActorEntity,Long> {
}
