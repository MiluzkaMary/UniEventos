package co.edu.uniquindio.unieventos.model.documents;

import co.edu.uniquindio.unieventos.model.vo.DetalleOrden;
import co.edu.uniquindio.unieventos.model.vo.Pago;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import org.bson.types.ObjectId;
import java.util.List;

@Document("ordenes")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Orden {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private ObjectId idCuenta;
    private ObjectId idCupon;
    private Pago pago;
    private LocalDateTime fecha;
    private String codigoQR;
    private List<DetalleOrden> items;
    private double total;

    @Builder
    public Orden(ObjectId idCuenta, ObjectId idCupon, Pago pago, LocalDateTime fecha, String codigoQR, List<DetalleOrden> items, double total) {

        this.idCuenta = idCuenta;
        this.idCupon = idCupon;
        this.pago = pago;
        this.fecha = LocalDateTime.now();
        this.codigoQR = codigoQR;
        this.items = items;
        this.total = total;
    }
}