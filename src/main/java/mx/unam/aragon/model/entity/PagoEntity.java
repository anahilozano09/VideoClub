package mx.unam.aragon.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "pago")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PagoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long id;

    @Column(name = "cantidad", nullable = false)
    private Double cantidad;

    @Column(name = "tarjeta", nullable = false)
    private String tarjeta;

    @ManyToOne
    @JoinColumn(name = "id_tipo", nullable = false)
    private TipoPagoEntity tipo;


}
