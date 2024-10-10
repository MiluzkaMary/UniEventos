package co.edu.uniquindio.unieventos.dto.cuenta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioDTO(
        @NotBlank String cedula,
        @NotBlank String nombre,
        @NotBlank String telefono,
        String direccion
) {}
