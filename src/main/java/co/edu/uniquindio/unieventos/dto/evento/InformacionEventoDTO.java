package co.edu.uniquindio.unieventos.dto.evento;

import co.edu.uniquindio.unieventos.model.enums.EstadoEvento;
import co.edu.uniquindio.unieventos.model.enums.TipoEvento;
import co.edu.uniquindio.unieventos.model.vo.Localidad;
import java.time.LocalDateTime;
import java.util.List;
public record InformacionEventoDTO(
    String id,
    String nombre,
    String descripcion,
    String ciudad,
    String direccion,
    LocalDateTime fecha,
    List<Localidad> localidades,
    TipoEvento tipo,
    EstadoEvento estado
) {
}
