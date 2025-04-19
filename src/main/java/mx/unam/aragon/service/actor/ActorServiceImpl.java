package mx.unam.aragon.service.actor;

import mx.unam.aragon.model.entity.ActorEntity;
import mx.unam.aragon.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService{
    @Autowired
    ActorRepository actorRepository;
    @Override
    @Transactional
    public ActorEntity save(ActorEntity actor) {
        return actorRepository.save(actor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActorEntity> findAll() {
        return actorRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        actorRepository.deleteById(id);
    }

    @Override
    public ActorEntity findById(Long id) {
        Optional<ActorEntity> op = actorRepository.findById(id);
        return op.orElse(null);
    }
}
