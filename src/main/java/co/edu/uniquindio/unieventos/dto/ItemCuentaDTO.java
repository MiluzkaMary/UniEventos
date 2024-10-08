package co.edu.uniquindio.unieventos.dto;

import co.edu.uniquindio.unieventos.model.enums.EstadoCuenta;
import co.edu.uniquindio.unieventos.model.enums.Rol;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.Email;

public record ItemCuentaDTO(
        @NotBlank String id,
        @NotBlank @Length(max = 50) @Email String correo,
        @NotBlank @Length(max = 100) String nombre,
        @NotBlank @Length(max = 10) String cedula,
        @NotBlank @Length(max = 10) String telefono
){
}
