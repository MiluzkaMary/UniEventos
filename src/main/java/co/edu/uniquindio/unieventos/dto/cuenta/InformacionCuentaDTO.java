package co.edu.uniquindio.unieventos.dto.cuenta;

import co.edu.uniquindio.unieventos.model.enums.EstadoCuenta;
import co.edu.uniquindio.unieventos.model.vo.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InformacionCuentaDTO(
        @NotBlank String id,
        @NotBlank String correo,
        @NotNull String contraseña,
        @NotNull Usuario usuario,
        EstadoCuenta estado
) {}
