package co.edu.uniquindio.unieventos.dto.evento;

import co.edu.uniquindio.unieventos.dto.carrito.DetalleCarritoDTO;
import co.edu.uniquindio.unieventos.model.vo.Localidad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record ItemEventoDTO(
        @NotBlank @Length(max = 100) String nombre,
        @NotBlank @Length(max = 500) String descripcion,
        @NotNull LocalDateTime fecha,
        @NotBlank String direccion
) {
}