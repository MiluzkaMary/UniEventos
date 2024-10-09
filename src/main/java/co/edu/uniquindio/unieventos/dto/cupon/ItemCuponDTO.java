package co.edu.uniquindio.unieventos.dto.cupon;

import co.edu.uniquindio.unieventos.model.enums.EstadoCupon;
import co.edu.uniquindio.unieventos.model.enums.TipoCupon;

import java.time.LocalDateTime;

public record ItemCuponDTO(
        String id,
        String nombre,
        double descuento,
        LocalDateTime fechaVencimiento,
        TipoCupon tipo
) {
}
