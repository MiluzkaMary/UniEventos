package co.edu.uniquindio.unieventos.dto.cuenta;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank @Email String correo,
        @NotBlank String password
        ) {
}
