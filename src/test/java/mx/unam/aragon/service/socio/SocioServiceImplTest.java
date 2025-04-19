package mx.unam.aragon.service.socio;

import mx.unam.aragon.model.entity.SocioEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SocioServiceImplTest {
    @Autowired
    SocioService socioService;

    @Test
    void salvar(){
        SocioEntity socio = SocioEntity.builder()
                .nombre("Sergio")
                .direccion("Jalisco")
                .telefono(1122334455L)
                .build();
        socioService.save(socio);
    }

    @Test
    void listar(){
        socioService.findAll().forEach(System.out::println);
    }
}