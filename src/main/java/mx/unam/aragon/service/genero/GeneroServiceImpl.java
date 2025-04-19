package mx.unam.aragon.service.genero;

import mx.unam.aragon.model.entity.GeneroEntity;
import mx.unam.aragon.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroServiceImpl implements GeneroService {
    @Autowired
    GeneroRepository generoRepository;

    @Override
    @Transactional
    public GeneroEntity save(GeneroEntity genero) {
        return generoRepository.save(genero);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GeneroEntity> findAll() {
        return generoRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        generoRepository.deleteById(id);
    }

    @Override
    public GeneroEntity findById(Long id) {
        Optional<GeneroEntity> op = generoRepository.findById(id);
        return op.orElse(null);
    }
}
