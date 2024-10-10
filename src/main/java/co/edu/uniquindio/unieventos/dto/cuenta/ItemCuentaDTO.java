package co.edu.uniquindio.unieventos.dto.cuenta;

import co.edu.uniquindio.unieventos.model.enums.EstadoCuenta;
import co.edu.uniquindio.unieventos.model.enums.Rol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.Email;

public record ItemCuentaDTO(
        @NotBlank String id,
        @NotBlank String correo,
        @NotNull UsuarioDTO usuario,
        EstadoCuenta estado
){
}
