package mx.unam.aragon.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "actor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actor")
    private Long id;

    @Column(name="nombre_real")
    private String nombreReal;

    @Column(name="nombre_artistico")
    private String nombreArtistico;

}
