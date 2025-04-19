package mx.unam.aragon.model.entity.listaEspera;

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
public class ListaEsperaId implements Serializable {
    @Column(name = "id_socio")
    private Long idSocio;

    @Column(name = "id_pelicula")
    private Long idPelicula;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListaEsperaId that = (ListaEsperaId) o;
        return Objects.equals(idSocio, that.idSocio) && Objects.equals(idPelicula, that.idPelicula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSocio, idPelicula);
    }
}
