package co.edu.uniquindio.unieventos.dto.orden;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

public record DetalleOrdenDTO(
        @NotBlank String id,
        @NotBlank ObjectId idEvento,
        @NotNull int cantidad,
        @NotBlank String nombreLocalidad,
        @NotNull double total,
        @NotNull double precioUnitario
) {
}