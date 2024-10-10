package co.edu.uniquindio.unieventos.dto.orden;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DetalleOrdenDTO(
        @NotBlank String idProducto,
        @NotBlank String idEvento,
        @NotBlank String nombreLocalidad,
        @NotNull int cantidad,
        @NotNull double precioUnitario,
        @NotNull double total
) {
}