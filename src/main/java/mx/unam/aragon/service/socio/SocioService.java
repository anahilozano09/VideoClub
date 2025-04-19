package mx.unam.aragon.service.socio;

import mx.unam.aragon.model.entity.SocioEntity;

import java.util.List;

public interface SocioService {
    SocioEntity save(SocioEntity socio);
    List<SocioEntity> findAll();
    void deleteById(Long id);
    SocioEntity findById(Long id);
}
