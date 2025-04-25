package mx.unam.aragon.service.pago;

import mx.unam.aragon.model.entity.PagoEntity;

import java.util.List;

public interface PagoService {
    PagoEntity save(PagoEntity pagoEntity);
    List<PagoEntity> findAll();
    void deleteById(Long id);
    PagoEntity findById(Long id);
}
