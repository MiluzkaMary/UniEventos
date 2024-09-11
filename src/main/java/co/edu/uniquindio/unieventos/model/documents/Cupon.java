package co.edu.uniquindio.unieventos.model.documents;

import co.edu.uniquindio.unieventos.model.enums.EstadoCupon;
import co.edu.uniquindio.unieventos.model.enums.TipoCupon;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document("cupones")

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cupon {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String codigo;
    private String nombre;
    private double descuento;
    private LocalDateTime fechaVencimiento;
    private EstadoCupon estado;
    private TipoCupon tipo;

    @Builder
    public Cupon(String codigo, String nombre, double descuento, LocalDateTime fechaVencimiento, EstadoCupon estado, TipoCupon tipo) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.descuento = descuento;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = estado;
        this.tipo = tipo;
    }





}
