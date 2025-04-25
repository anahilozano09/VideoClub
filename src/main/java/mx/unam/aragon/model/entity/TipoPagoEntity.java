package mx.unam.aragon.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tipo_pago")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoPagoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "digitos")
    private Integer digitos;
}
