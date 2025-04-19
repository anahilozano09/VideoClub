package mx.unam.aragon.service.prestamo;

import mx.unam.aragon.model.entity.PeliculaEntity;
import mx.unam.aragon.model.entity.PrestamoEntity;

import java.util.List;

public interface PrestamoService {
    PrestamoEntity save(PrestamoEntity prestamo);
    List<PrestamoEntity> findAll();
    void deleteById(Long id);
    PrestamoEntity findById(Long id);
}
