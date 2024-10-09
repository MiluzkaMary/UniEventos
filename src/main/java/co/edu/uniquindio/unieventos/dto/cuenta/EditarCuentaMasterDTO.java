package co.edu.uniquindio.unieventos.dto.cuenta;


import co.edu.uniquindio.unieventos.model.enums.EstadoCuenta;
import co.edu.uniquindio.unieventos.model.enums.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EditarCuentaMasterDTO(
        @NotBlank String id,
        @NotBlank @Length(max = 50) @Email String correo,
        @NotBlank @Length(max = 10) String cedula,
        @NotBlank @Length(max = 100) String nombre,
        @Length(max = 100) String direccion,
        @NotBlank @Length(max = 10) String telefono,
        @NotBlank @Length(min = 7, max = 20) String password,
        EstadoCuenta estado,
        Rol rol
) {
}
