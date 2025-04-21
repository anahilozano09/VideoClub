package mx.unam.aragon.model.entity.ejemplar;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.unam.aragon.model.entity.PeliculaEntity;

@Entity(name = "ejemplar")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class EjemplarEntity {
    @EmbeddedId
    private EjemplarId id;

    @ManyToOne
    @MapsId(value = "idPelicula")
    @JoinColumn(name = "id_pelicula", nullable = false)
    private PeliculaEntity pelicula;

    public Long getConsecutivo() {
        return id != null ? id.getConsecutivo() : null;
    }

    public void setConsecutivo(Long consecutivo) {
        if (id == null) {
            id = new EjemplarId();
        }
        id.setConsecutivo(consecutivo);
    }

}
