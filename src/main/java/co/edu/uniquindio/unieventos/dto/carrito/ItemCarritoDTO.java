package co.edu.uniquindio.unieventos.dto.carrito;

import co.edu.uniquindio.unieventos.model.vo.DetalleCarrito;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

public record ItemCarritoDTO(
        @NotBlank String id,
        @NotNull ObjectId idCuenta,
        @NotNull LocalDateTime fecha,
        @NotEmpty List<DetalleCarritoDTO> items
) {
}
