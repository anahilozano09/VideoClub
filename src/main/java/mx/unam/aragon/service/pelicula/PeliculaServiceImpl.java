package mx.unam.aragon.service.pelicula;

import mx.unam.aragon.model.entity.PeliculaEntity;
import mx.unam.aragon.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServiceImpl implements PeliculaService{
    @Autowired
    private PeliculaRepository peliculaRepository;
    @Override
    @Transactional
    public PeliculaEntity save(PeliculaEntity pelicula) {
        return peliculaRepository.save(pelicula);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PeliculaEntity> findAll() {
        return peliculaRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        peliculaRepository.deleteById(id);
    }

    @Override
    public PeliculaEntity findById(Long id) {
        Optional<PeliculaEntity> op = peliculaRepository.findById(id);
        return op.orElse(null);
    }
}
