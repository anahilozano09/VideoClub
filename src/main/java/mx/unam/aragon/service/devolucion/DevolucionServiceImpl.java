package mx.unam.aragon.service.devolucion;

import mx.unam.aragon.model.entity.DevolucionEntity;
import mx.unam.aragon.repository.DevolucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DevolucionServiceImpl implements DevolucionService {
    @Autowired
    DevolucionRepository devolucionRepository;

    @Override
    @Transactional
    public DevolucionEntity save(DevolucionEntity devolucion) {
        return devolucionRepository.save(devolucion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DevolucionEntity> findAll() {
        return devolucionRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        devolucionRepository.deleteById(id);
    }

    @Override
    public DevolucionEntity findById(Long id) {
        Optional<DevolucionEntity> op = devolucionRepository.findById(id);
        return op.orElse(null);
    }
}
