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
public class DetalleCarrito {

    private ObjectId idEvento;
    private int cantidad;
    private String nombreLocalidad;

}
