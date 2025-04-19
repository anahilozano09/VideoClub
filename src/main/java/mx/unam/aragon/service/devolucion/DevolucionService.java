package mx.unam.aragon.service.devolucion;

import mx.unam.aragon.model.entity.DevolucionEntity;

import java.util.List;

public interface DevolucionService {
    DevolucionEntity save(DevolucionEntity devolucion);
    List<DevolucionEntity> findAll();
    void deleteById(Long id);
    DevolucionEntity findById(Long id);
}
