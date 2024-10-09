package co.edu.uniquindio.unieventos.dto.cupon;

import co.edu.uniquindio.unieventos.model.enums.EstadoCupon;
import co.edu.uniquindio.unieventos.model.enums.TipoCupon;

import java.time.LocalDateTime;

public record InformacionCuponDTO(
        String id,
        String codigo,
        String nombre,
        double descuento,
        LocalDateTime fechaVencimiento,
        EstadoCupon estado,
        TipoCupon tipo
) {
}
