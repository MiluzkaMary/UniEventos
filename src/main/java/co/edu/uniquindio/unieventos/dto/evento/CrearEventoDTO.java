package co.edu.uniquindio.unieventos.dto.evento;

import co.edu.uniquindio.unieventos.model.enums.EstadoEvento;
import co.edu.uniquindio.unieventos.model.enums.TipoEvento;
import co.edu.uniquindio.unieventos.model.vo.Localidad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Future;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

public record CrearEventoDTO(
        @NotBlank @Length(max = 100) String nombre,
        @NotBlank @Length(max = 500) String descripcion,
        @NotBlank String ciudad,
        @NotBlank String direccion,
        @Future @NotNull LocalDateTime fecha,
        @NotNull List<LocalidadDTO> localidades,
        @NotBlank String imagenPortada,
        @NotBlank String imagenLocalidades,
        @NotNull TipoEvento tipo,
        @NotNull EstadoEvento estado
) {
}