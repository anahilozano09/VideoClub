package mx.unam.aragon.service.tipoPago;

import mx.unam.aragon.model.entity.TipoPagoEntity;

import java.util.List;

public interface TipoPagoService {
    TipoPagoEntity save(TipoPagoEntity tipoPagoEntity);
    List<TipoPagoEntity> findAll();
    void deleteById(Long id);
    TipoPagoEntity findById(Long id);
}
