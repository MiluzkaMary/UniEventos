package co.edu.uniquindio.unieventos.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;


@AllArgsConstructor
@Getter
@Setter
public class DetalleCarrito {

    private ObjectId idEvento;
    private int cantidad;
    private String nombreLocalidad;

}
