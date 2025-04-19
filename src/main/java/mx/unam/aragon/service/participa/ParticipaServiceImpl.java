package mx.unam.aragon.service.participa;

import mx.unam.aragon.model.entity.PeliculaEntity;
import mx.unam.aragon.model.entity.participa.ParticipaEntity;
import mx.unam.aragon.model.entity.participa.ParticipaId;
import mx.unam.aragon.repository.ParticipaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipaServiceImpl implements ParticipaService {

    @Autowired
    private ParticipaRepository participaRepository;

    @Override
    @Transactional
    public ParticipaEntity save(ParticipaEntity participa) {
        return participaRepository.save(participa);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ParticipaEntity> findAll() {
        return participaRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(ParticipaId id) {
        participaRepository.deleteById(id);
    }

    @Override
    public ParticipaEntity findById(ParticipaId id) {
        Optional<ParticipaEntity> op = participaRepository.findById(id);
        return op.orElse(null);
    }

    @Override
    public boolean existsById(ParticipaId id) {
        return participaRepository.existsById(id);
    }


}
