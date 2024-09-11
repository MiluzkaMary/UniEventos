package co.edu.uniquindio.unieventos.model.documents;

import co.edu.uniquindio.unieventos.model.vo.DetalleCarrito;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("carritos")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Carrito {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private ObjectId idCuenta;
    private LocalDateTime fecha;
    private List<DetalleCarrito> items;

    @Builder
    public Carrito(ObjectId idCuenta, LocalDateTime fecha, List<DetalleCarrito> items) {
        this.idCuenta = idCuenta;
        this.fecha = LocalDateTime.now();
        this.items = items;
    }
}
