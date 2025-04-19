package mx.unam.aragon.service;

import mx.unam.aragon.model.entity.ActorEntity;
import mx.unam.aragon.service.actor.ActorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ActorServiceImplTest {
    @Autowired
    ActorService actorService;

    @Test
    void save(){
        ActorEntity actor=ActorEntity.builder()
                .nombreReal("Benito bodoque")
                .nombreArtistico("Benito")
                .build();
        System.out.println(actor);
        actorService.save(actor);
    }
}