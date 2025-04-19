package mx.unam.aragon.service.prestamo;

import mx.unam.aragon.model.entity.PeliculaEntity;
import mx.unam.aragon.model.entity.PrestamoEntity;
import mx.unam.aragon.repository.PrestamoRepository;
import mx.unam.aragon.service.socio.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoServiceImpl implements PrestamoService {
    @Autowired
    PrestamoRepository prestamoRepository;

    @Override
    @Transactional
    public PrestamoEntity save(PrestamoEntity prestamo) {
        return prestamoRepository.save(prestamo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PrestamoEntity> findAll() {
        return prestamoRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        prestamoRepository.deleteById(id);
    }

    @Override
    public PrestamoEntity findById(Long id) {
        Optional <PrestamoEntity> op = prestamoRepository.findById(id);
        return op.orElse(null);
    }
}
