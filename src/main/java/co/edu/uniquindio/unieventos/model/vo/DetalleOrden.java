package co.edu.uniquindio.unieventos.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;


@AllArgsConstructor
@Getter
@Setter
@Builder
public class DetalleOrden {

    private String id;
    private ObjectId idEvento;
    private int cantidad;
    private String nombreLocalidad;
    private double precio;
    private double precioUnitario;
}
