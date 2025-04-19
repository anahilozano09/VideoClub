package mx.unam.aragon.service.participa;

import mx.unam.aragon.model.entity.ActorEntity;
import mx.unam.aragon.model.entity.PeliculaEntity;
import mx.unam.aragon.model.entity.participa.ParticipaEntity;
import mx.unam.aragon.model.entity.participa.ParticipaId;
import mx.unam.aragon.repository.PeliculaRepository;
import mx.unam.aragon.service.actor.ActorService;
import mx.unam.aragon.service.pelicula.PeliculaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ParticipaServiceImplTest {
    @Autowired
    ParticipaService participaService;
    @Autowired
    PeliculaService peliculaService;
    @Autowired
    ActorService actorService;

    @Test
    void salvar(){
        PeliculaEntity pelicula = peliculaService.findById(8L);
        ActorEntity actor = actorService.findById(11L);
        ParticipaId participaId = new ParticipaId(8L,11L);
        ParticipaEntity participa = ParticipaEntity.builder()
                .id(participaId)
                .pelicula(pelicula)
                .actor(actor)
                .build();
        participaService.save(participa);
    }

    @Test
    void datos(){
        participaService.findAll().forEach(System.out::println);
    }
}