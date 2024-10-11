package co.edu.uniquindio.unieventos.model.vo;

import java.time.LocalDateTime;

import co.edu.uniquindio.unieventos.model.enums.Pasarela;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
@Builder
public class Pago {
    private String codigo;
    private LocalDateTime fecha;
    private String estado;
    private String detalleEstado;
    private Pasarela metodoPago;
    private String moneda;
    private String codigoAutorizacion;
    private double totalPago;
}
