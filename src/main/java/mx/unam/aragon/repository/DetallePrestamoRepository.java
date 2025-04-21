package mx.unam.aragon.repository;

import mx.unam.aragon.model.entity.detallePrestamo.DetallePrestamoEntity;
import mx.unam.aragon.model.entity.detallePrestamo.DetallePrestamoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallePrestamoRepository extends JpaRepository<DetallePrestamoEntity, DetallePrestamoId> {
}
