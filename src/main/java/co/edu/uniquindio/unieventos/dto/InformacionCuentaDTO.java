package co.edu.uniquindio.unieventos.dto;

import co.edu.uniquindio.unieventos.model.enums.EstadoCuenta;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.Email;

import java.time.LocalDateTime;



public record InformacionCuentaDTO(

        @NotBlank String id,
        @NotBlank @Length(max = 50) @Email String correo,
        @NotBlank @Length(max = 10) String cedula,
        @NotBlank @Length(max = 100) String nombre,
        @Length(max = 100) String direccion,
        @NotBlank @Length(max = 10) String telefono,
        LocalDateTime fechaNacimiento,

        EstadoCuenta estado) {
}
