package mx.unam.aragon.model.entity.participa;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ParticipaId implements Serializable {

    @Column(name = "id_pelicula")
    private Long idPelicula;

    @Column(name = "id_actor")
    private Long idActor;

    public ParticipaId() {

    }
    public ParticipaId(Long idPelicula, Long idActor) {
        this.idPelicula = idPelicula;
        this.idActor = idActor;
    }

    public Long getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Long idPelicula) {
        this.idPelicula = idPelicula;
    }

    public Long getIdActor() {
        return idActor;
    }

    public void setIdActor(Long idActor) {
        this.idActor = idActor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipaId that = (ParticipaId) o;
        return Objects.equals(idPelicula, that.idPelicula) && Objects.equals(idActor, that.idActor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPelicula, idActor);
    }
}
