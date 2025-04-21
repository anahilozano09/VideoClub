package mx.unam.aragon.model.entity.detallePrestamo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.unam.aragon.model.entity.PeliculaEntity;
import mx.unam.aragon.model.entity.PrestamoEntity;
import mx.unam.aragon.model.entity.ejemplar.EjemplarEntity;

@Entity(name = "detalle_prestamo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class DetallePrestamoEntity {
    @EmbeddedId
    private DetallePrestamoId id;

    @ManyToOne
    @MapsId("idPrestamo")
    @JoinColumn(name = "id_prestamo", nullable = false)
    private PrestamoEntity prestamo;

    @ManyToOne
    @MapsId("idPelicula")
    @JoinColumn(name = "id_pelicula", nullable = false)
    private PeliculaEntity pelicula;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(
                    name = "id_pelicula",
                    referencedColumnName = "id_pelicula",
                    insertable = false,
                    updatable = false),
            @JoinColumn(
                    name = "consecutivo",
                    referencedColumnName = "consecutivo",
                    insertable = false,
                    updatable = false)
    })
    private EjemplarEntity ejemplar;
}
