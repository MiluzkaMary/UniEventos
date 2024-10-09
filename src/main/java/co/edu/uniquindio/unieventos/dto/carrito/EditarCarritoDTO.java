package co.edu.uniquindio.unieventos.dto.carrito;

import co.edu.uniquindio.unieventos.model.vo.DetalleCarrito;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

public record EditarCarritoDTO(
        String id,                  // ID del carrito a editar
        ObjectId idCuenta,         // ID de la cuenta asociada al carrito
        LocalDateTime fecha,       // Fecha del carrito
        List<DetalleCarrito> items // Lista de detalles del carrito
) {
}
