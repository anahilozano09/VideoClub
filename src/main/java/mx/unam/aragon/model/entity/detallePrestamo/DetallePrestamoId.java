package mx.unam.aragon.model.entity.detallePrestamo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetallePrestamoId {
    @Column(name = "id_prestamo")
    private Long idPrestamo;

    @Column(name = "id_pelicula")
    private Long idPelicula;

    @Column(name = "consecutivo")
    private Long consecutivo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetallePrestamoId that = (DetallePrestamoId) o;
        return Objects.equals(idPrestamo, that.idPrestamo) && Objects.equals(idPelicula, that.idPelicula) && Objects.equals(consecutivo, that.consecutivo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPrestamo, idPelicula, consecutivo);
    }
}
