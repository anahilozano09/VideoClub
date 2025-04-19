package mx.unam.aragon.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "devolucion")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class DevolucionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_devolucion")
    private Long id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "id_prestamo",nullable = false)
    private PrestamoEntity prestamo;
}
