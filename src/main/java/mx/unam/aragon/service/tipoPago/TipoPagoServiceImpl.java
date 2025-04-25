package mx.unam.aragon.service.tipoPago;

import mx.unam.aragon.model.entity.TipoPagoEntity;
import mx.unam.aragon.repository.TipoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TipoPagoServiceImpl implements TipoPagoService{
    @Autowired
    TipoPagoRepository tipoPagoRepository;

    @Override
    @Transactional
    public TipoPagoEntity save(TipoPagoEntity tipoPagoEntity) {
        return tipoPagoRepository.save(tipoPagoEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoPagoEntity> findAll() {
        return tipoPagoRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        tipoPagoRepository.deleteById(id);
    }

    @Override
    public TipoPagoEntity findById(Long id) {
        Optional<TipoPagoEntity> op = tipoPagoRepository.findById(id);
        return op.orElse(null);
    }
}
