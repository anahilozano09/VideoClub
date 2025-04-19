package mx.unam.aragon.service.genero;

import mx.unam.aragon.model.entity.GeneroEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GeneroServiceImplTest {
    @Autowired
    GeneroService generoService;

    @Test
    void salvar(){
        GeneroEntity genero = GeneroEntity.builder()
                .descripcion("Comedia Romantica")
                .build();
        generoService.save(genero);
    }

    @Test
    void datos(){
        generoService.findAll().forEach(System.out::println);
    }

}