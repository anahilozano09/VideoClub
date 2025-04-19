package mx.unam.aragon.service.director;


import mx.unam.aragon.model.entity.DirectorEntity;
import mx.unam.aragon.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class DirectorServiceImpl implements DirectorService{
    @Autowired
    DirectorRepository directorRepository;
    @Override
     @Transactional
    public DirectorEntity save(DirectorEntity director) {
        return directorRepository.save(director);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DirectorEntity> findAll() {

        return directorRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        directorRepository.deleteById(id);
    }

    @Override
    public DirectorEntity findById(Long id) {
        Optional<DirectorEntity> op=directorRepository.findById(id);
        return op.orElse(null);
    }
}
