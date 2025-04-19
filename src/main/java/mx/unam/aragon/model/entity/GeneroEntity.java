package mx.unam.aragon.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "genero")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeneroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genero")
    private Long id;

    @Column(name="descripcion")
    private String descripcion;

    

}
