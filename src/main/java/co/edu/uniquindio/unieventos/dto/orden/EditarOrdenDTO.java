package co.edu.uniquindio.unieventos.dto.orden;

import co.edu.uniquindio.unieventos.model.vo.DetalleOrden;
import co.edu.uniquindio.unieventos.model.vo.Pago;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import java.time.LocalDateTime;
import java.util.List;

public record EditarOrdenDTO(
        @NotBlank String id,
        @NotNull ObjectId idCuenta,
        @NotNull ObjectId idCupon,
        @NotNull PagoDTO pago,
        @NotNull LocalDateTime fecha,
        @NotBlank String codigoQR,
        @NotNull List<DetalleOrdenDTO> items,
        @NotNull double total
) {
}