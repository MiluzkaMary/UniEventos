package co.edu.uniquindio.unieventos.dto.orden;

import co.edu.uniquindio.unieventos.model.enums.Pasarela;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PagoDTO (
        @NotNull String codigo,
        @NotNull LocalDateTime fecha,
        @NotNull double total,
        @NotNull String estadoPago,
        @NotNull String detalleEstado,
        @NotNull Pasarela metodoPago,
        @NotNull String moneda,
        @NotNull String codigoAutorizacion
) {

}

