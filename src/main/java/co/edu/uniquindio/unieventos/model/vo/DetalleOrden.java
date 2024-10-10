package co.edu.uniquindio.unieventos.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
@Builder
public class DetalleOrden {

    private String id;
    private String idEvento;
    private int cantidad;
    private String nombreLocalidad;
    private double precio;
    private double precioUnitario;
}
