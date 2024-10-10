package co.edu.uniquindio.unieventos.dto.cuenta;

import co.edu.uniquindio.unieventos.model.enums.EstadoCuenta;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record EditarCuentaDTO(
        @NotBlank String id,
        @NotBlank @Email @Length(max = 50) String correo,
        @NotBlank @Length(min = 8, max = 20) String password,
        @NotNull UsuarioDTO usuario,  // Incluir UsuarioDTO para la edición
        EstadoCuenta estado
) {}
