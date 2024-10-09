package co.edu.uniquindio.unieventos.dto.carrito;

import co.edu.uniquindio.unieventos.model.vo.DetalleCarrito;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

public record InformacionCarritoDTO(
        String id,                  // ID del carrito
        ObjectId idCuenta,         // ID de la cuenta asociada al carrito
        LocalDateTime fecha,       // Fecha de creación o modificación del carrito
        List<DetalleCarrito> items // Lista de detalles del carrito
) {
}
