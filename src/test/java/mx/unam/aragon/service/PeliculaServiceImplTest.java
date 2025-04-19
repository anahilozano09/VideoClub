package mx.unam.aragon.service;

import mx.unam.aragon.model.entity.DirectorEntity;
import mx.unam.aragon.model.entity.GeneroEntity;
import mx.unam.aragon.model.entity.PeliculaEntity;
import mx.unam.aragon.service.pelicula.PeliculaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PeliculaServiceImplTest {
    @Autowired
    PeliculaService peliculaService;

    @Test
    void salvar(){
        DirectorEntity director= DirectorEntity.builder()
                .id(1L)
                .nombre("LARISSA RODRIGUEZ")
                .build();
        GeneroEntity genero=GeneroEntity.builder()
                .id(1L)
                .descripcion("Terror")
                .build();
        PeliculaEntity pelicula= PeliculaEntity.builder()
                .titulo("Spring boot")
                .anio(2025)
                .precio(123.34)
                .director(director)
                .genero(genero)
                .build();
        peliculaService.save(pelicula);
    }
}