package mx.unam.aragon.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "prestamo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PrestamoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestamo")
    private Long id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "f_Prog_Dev")
    private LocalDate fProgDev;

    @Column(name = "total")
    private Double total;

    @ManyToOne
    @JoinColumn(name = "id_socio", nullable = false)
    private SocioEntity socio;
}
