package mx.unam.aragon.service.listaEspera;

import mx.unam.aragon.model.entity.listaEspera.ListaEsperaEntity;
import mx.unam.aragon.model.entity.listaEspera.ListaEsperaId;
import mx.unam.aragon.model.entity.participa.ParticipaEntity;
import mx.unam.aragon.model.entity.participa.ParticipaId;
import mx.unam.aragon.repository.ListaEsperaRepository;
import mx.unam.aragon.repository.ParticipaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ListaEsperaServiceImpl implements ListaEsperaService {
    @Autowired
    ListaEsperaRepository listaEsperaRepository;

    @Override
    @Transactional
    public ListaEsperaEntity save(ListaEsperaEntity listaEspera) {
        return listaEsperaRepository.save(listaEspera);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ListaEsperaEntity> findAll() {
        return listaEsperaRepository.findAll();
    }

    @Override
    public void deleteById(ListaEsperaId id) {
        listaEsperaRepository.deleteById(id);
    }

    @Override
    public ListaEsperaEntity findById(ListaEsperaId id) {
        Optional<ListaEsperaEntity> op = listaEsperaRepository.findById(id);
        return op.orElse(null);
    }

    @Override
    public boolean existsById(ListaEsperaId id) {
        return listaEsperaRepository.existsById(id);
    }
}
