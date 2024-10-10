package co.edu.uniquindio.unieventos.dto.carrito;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

public record DetalleCarritoDTO(
        @NotNull ObjectId idEvento,
        @NotNull int cantidad,
        @NotBlank String nombreLocalidad
) {
}
