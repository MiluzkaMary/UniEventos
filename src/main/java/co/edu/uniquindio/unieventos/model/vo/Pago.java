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

    private LocalDateTime fecha;
    private double totalPago;
    private String estado;
    private Pasarela metodoPago;

}
