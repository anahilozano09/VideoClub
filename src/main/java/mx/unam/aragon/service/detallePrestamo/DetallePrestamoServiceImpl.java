package mx.unam.aragon.service.detallePrestamo;

import mx.unam.aragon.model.entity.detallePrestamo.DetallePrestamoEntity;
import mx.unam.aragon.model.entity.detallePrestamo.DetallePrestamoId;
import mx.unam.aragon.repository.DetallePrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service

public class DetallePrestamoServiceImpl implements DetallePrestamoService{
    @Autowired
    DetallePrestamoRepository detallePrestamoRepository;

    @Override
    @Transactional
    public DetallePrestamoEntity save(DetallePrestamoEntity detallePrestamoEntity) {
        return detallePrestamoRepository.save(detallePrestamoEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetallePrestamoEntity> findAll() {
        return detallePrestamoRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(DetallePrestamoId id) {
        detallePrestamoRepository.deleteById(id);
    }

    @Override
    public DetallePrestamoEntity findById(DetallePrestamoId id) {
        Optional<DetallePrestamoEntity> op = detallePrestamoRepository.findById(id);
        return op.orElse(null);
    }

    @Override
    public boolean existsById(DetallePrestamoId id) {
        return detallePrestamoRepository.existsById(id);
    }
}
