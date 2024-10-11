package co.edu.uniquindio.unieventos.dto.orden;

import co.edu.uniquindio.unieventos.model.vo.DetalleOrden;
import co.edu.uniquindio.unieventos.model.vo.Pago;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import java.time.LocalDateTime;
import java.util.List;

public record InformacionOrdenDTO(
        @NotBlank String id,
        @NotNull ObjectId idCuenta,
        @NotNull ObjectId idCupon,
        @NotNull Pago pago,
        @NotNull LocalDateTime fecha,
        @NotBlank String codigoQR,
        @NotNull List<DetalleOrden> items,
        @NotNull double total,
        @NotNull String codigoPasarela
) {
}