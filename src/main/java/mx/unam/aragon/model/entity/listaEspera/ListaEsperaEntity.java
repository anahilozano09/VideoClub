package mx.unam.aragon.model.entity.listaEspera;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.unam.aragon.model.entity.PeliculaEntity;
import mx.unam.aragon.model.entity.SocioEntity;

import java.time.LocalDate;

@Entity(name = "lista_espera")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListaEsperaEntity {
    @EmbeddedId
    private ListaEsperaId id;

    @ManyToOne
    @MapsId("idSocio")
    @JoinColumn(name = "id_socio", nullable = false)
    private SocioEntity socio;

    @ManyToOne
    @MapsId("idPelicula")
    @JoinColumn(name = "id_pelicula", nullable = false)
    private PeliculaEntity pelicula;

    @Column(name = "fecha")
    private LocalDate fecha;
}
