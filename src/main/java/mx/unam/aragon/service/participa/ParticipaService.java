package mx.unam.aragon.service.participa;

import mx.unam.aragon.model.entity.participa.ParticipaEntity;
import mx.unam.aragon.model.entity.participa.ParticipaId;

import java.util.List;

public interface ParticipaService {
    ParticipaEntity save(ParticipaEntity participa);
    List<ParticipaEntity> findAll();
    void deleteById(ParticipaId id);
    ParticipaEntity findById(ParticipaId id);
    boolean existsById(ParticipaId id);
}
