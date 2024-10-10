package co.edu.uniquindio.unieventos.dto.evento;

import co.edu.uniquindio.unieventos.model.enums.EstadoEvento;
import co.edu.uniquindio.unieventos.model.enums.TipoEvento;
import co.edu.uniquindio.unieventos.model.vo.Localidad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

public record InformacionEventoDTO(
        @NotBlank String id,
        @NotBlank @Length(max = 100) String nombre,
        @NotBlank @Length(max = 500) String descripcion,
        @NotBlank String ciudad,
        @NotBlank String direccion,
        @NotNull LocalDateTime fecha,
        @NotNull List<Localidad> localidades,
        @NotNull TipoEvento tipo,
        @NotNull EstadoEvento estado
) {
}