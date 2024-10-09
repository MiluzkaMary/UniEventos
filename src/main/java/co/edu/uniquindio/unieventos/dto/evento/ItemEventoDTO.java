package co.edu.uniquindio.unieventos.dto.evento;

import java.time.LocalDateTime;

public record ItemEventoDTO(
    String nombre,
    String descripcion,
    LocalDateTime fecha,
    String direccion
    
) {
}
