package mx.unam.aragon.service.ejemplar;

import mx.unam.aragon.model.entity.ejemplar.EjemplarEntity;
import mx.unam.aragon.model.entity.ejemplar.EjemplarId;
import mx.unam.aragon.repository.EjemplarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service

public class EjemplarServiceImpl implements EjemplarService{
    @Autowired
    EjemplarRepository ejemplarRepository;

    @Override
    @Transactional
    public EjemplarEntity save(EjemplarEntity ejemplar) {
        return ejemplarRepository.save(ejemplar);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EjemplarEntity> findAll() {
        return ejemplarRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(EjemplarId id) {
        ejemplarRepository.deleteById(id);
    }

    @Override
    public EjemplarEntity findById(EjemplarId id) {
        Optional<EjemplarEntity> op = ejemplarRepository.findById(id);
        return op.orElse(null);
    }

    @Override
    public boolean existsById(EjemplarId id) {
        return ejemplarRepository.existsById(id);
    }
}
