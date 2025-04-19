package mx.unam.aragon.model.entity.participa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.unam.aragon.model.entity.ActorEntity;
import mx.unam.aragon.model.entity.PeliculaEntity;

@Entity(name = "participa")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParticipaEntity {
    @EmbeddedId
    private ParticipaId id;

    @ManyToOne
    @MapsId("idPelicula")
    @JoinColumn(name = "id_pelicula", nullable = false)
    private PeliculaEntity pelicula;

    @ManyToOne
    @MapsId("idActor")
    @JoinColumn(name = "id_actor", nullable = false)
    private ActorEntity actor;

}
