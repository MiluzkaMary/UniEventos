package co.edu.uniquindio.unieventos.dto.carrito;

import co.edu.uniquindio.unieventos.model.vo.DetalleCarrito;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

public record ItemCarritoDTO(
        String id,
        ObjectId idCuenta,
        LocalDateTime fecha,
        List<DetalleCarrito> items
) {
}
