package co.edu.uniquindio.unieventos.dto.cupon;

import co.edu.uniquindio.unieventos.model.enums.EstadoCupon;
import co.edu.uniquindio.unieventos.model.enums.TipoCupon;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Future;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record ItemCuponDTO(
        @NotBlank String id,
        @NotBlank @Length(max = 100) String nombre,
        @Positive double descuento,
        @Future @NotNull LocalDateTime fechaVencimiento,
        @NotNull TipoCupon tipo
) {
}