package mx.unam.aragon.service.listaEspera;

import mx.unam.aragon.model.entity.listaEspera.ListaEsperaEntity;
import mx.unam.aragon.model.entity.listaEspera.ListaEsperaId;
import mx.unam.aragon.model.entity.participa.ParticipaId;

import java.util.List;

public interface ListaEsperaService {
    ListaEsperaEntity save(ListaEsperaEntity listaEspera);
    List<ListaEsperaEntity> findAll();
    void deleteById(ListaEsperaId id);
    ListaEsperaEntity findById(ListaEsperaId id);
    boolean existsById(ListaEsperaId id);
}
