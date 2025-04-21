package mx.unam.aragon.model.entity.ejemplar;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EjemplarId implements Serializable {
    @Column(name = "id_pelicula")
    private Long idPelicula;

    @Column(name = "consecutivo")
    private Long consecutivo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EjemplarId that = (EjemplarId) o;
        return Objects.equals(idPelicula, that.idPelicula) && Objects.equals(consecutivo, that.consecutivo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPelicula, consecutivo);
    }
}
