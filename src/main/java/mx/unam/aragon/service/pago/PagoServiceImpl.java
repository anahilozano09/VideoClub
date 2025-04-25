package mx.unam.aragon.service.pago;

import mx.unam.aragon.model.entity.PagoEntity;
import mx.unam.aragon.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service

public class PagoServiceImpl implements PagoService{
    @Autowired
    PagoRepository pagoRepository;


    @Override
    @Transactional
    public PagoEntity save(PagoEntity pagoEntity) {
        return pagoRepository.save(pagoEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PagoEntity> findAll() {
        return pagoRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        pagoRepository.deleteById(id);
    }

    @Override
    public PagoEntity findById(Long id) {
        Optional<PagoEntity> op = pagoRepository.findById(id);
        return op.orElse(null);
    }
}
