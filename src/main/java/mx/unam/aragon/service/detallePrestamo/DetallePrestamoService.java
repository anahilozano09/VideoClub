package mx.unam.aragon.service.detallePrestamo;

import mx.unam.aragon.model.entity.detallePrestamo.DetallePrestamoEntity;
import mx.unam.aragon.model.entity.detallePrestamo.DetallePrestamoId;

import java.util.List;

public interface DetallePrestamoService {
    DetallePrestamoEntity save(DetallePrestamoEntity detallePrestamoEntity);
    List<DetallePrestamoEntity> findAll();
    void deleteById(DetallePrestamoId id);
    DetallePrestamoEntity findById(DetallePrestamoId id);
    boolean existsById(DetallePrestamoId id);
}
