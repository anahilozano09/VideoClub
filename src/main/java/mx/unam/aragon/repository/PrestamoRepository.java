package mx.unam.aragon.repository;

import mx.unam.aragon.model.entity.PrestamoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoRepository extends JpaRepository<PrestamoEntity, Long> {
}
