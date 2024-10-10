package co.edu.uniquindio.unieventos.dto.cuenta;

import co.edu.uniquindio.unieventos.model.enums.Rol;
import co.edu.uniquindio.unieventos.model.vo.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDateTime;

public record CrearCuentaDTO(
        @NotBlank @Email @Length(max = 50) String correo,
        @NotBlank @Length(min = 8, max = 20) String password,
        @NotNull Rol rol,
        @NotNull LocalDateTime fechaNacimiento,
        UsuarioDTO usuario
) {}
