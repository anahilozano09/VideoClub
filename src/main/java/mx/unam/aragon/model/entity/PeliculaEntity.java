package mx.unam.aragon.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "pelicula")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PeliculaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelicula")
    private Long id;

    @Column(name="titulo")
    private String titulo;

    @Column(name="anio")
    private Integer anio;

    @Column(name="precio")
    private Double precio;


    @ManyToOne
    @JoinColumn(name = "id_genero",nullable = false)
    private GeneroEntity genero;


    @ManyToOne
    @JoinColumn(name = "id_director",nullable = false)
    private DirectorEntity director;




}
