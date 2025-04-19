package mx.unam.aragon.service.socio;

import mx.unam.aragon.model.entity.SocioEntity;
import mx.unam.aragon.repository.SocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SocioServiceImpl implements SocioService {
    @Autowired
    SocioRepository socioRepository;

    @Override
    @Transactional
    public SocioEntity save(SocioEntity socio) {
        return socioRepository.save(socio);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SocioEntity> findAll() {
        return socioRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        socioRepository.deleteById(id);
    }

    @Override
    public SocioEntity findById(Long id) {
        Optional<SocioEntity> op = socioRepository.findById(id);
        return op.orElse(null);
    }
}
