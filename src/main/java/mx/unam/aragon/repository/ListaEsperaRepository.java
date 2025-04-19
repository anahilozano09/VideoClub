package mx.unam.aragon.repository;

import mx.unam.aragon.model.entity.listaEspera.ListaEsperaEntity;
import mx.unam.aragon.model.entity.listaEspera.ListaEsperaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListaEsperaRepository extends JpaRepository<ListaEsperaEntity, ListaEsperaId> {
}
