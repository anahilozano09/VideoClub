package mx.unam.aragon.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "director")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DirectorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_director")
    private Long id;

    @Column(name="nombre")
    @NotBlank
    private String nombre;



}
