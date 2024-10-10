package co.edu.uniquindio.unieventos.dto.cupon;

import co.edu.uniquindio.unieventos.model.enums.EstadoCupon;
import co.edu.uniquindio.unieventos.model.enums.TipoCupon;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Future;

import java.time.LocalDateTime;

public record CrearCuponDTO(
        @NotBlank String codigo,
        @NotBlank String nombre,
        @Positive double descuento,
        @Future @NotNull LocalDateTime fechaVencimiento,
        @NotNull EstadoCupon estado,
        @NotNull TipoCupon tipo
) {
}
