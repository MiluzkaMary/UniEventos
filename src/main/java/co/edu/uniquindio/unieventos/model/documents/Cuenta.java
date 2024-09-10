package co.edu.uniquindio.unieventos.model.documents;

import co.edu.uniquindio.unieventos.model.enums.EstadoCuenta;
import co.edu.uniquindio.unieventos.model.enums.Rol;
import co.edu.uniquindio.unieventos.model.vo.CodigoValidacion;
import co.edu.uniquindio.unieventos.model.vo.Usuario;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document ("cuentas")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Cuenta {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String correo;
    private String password;
    private Rol rol;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaNacimiento;
    private Usuario usuario;
    private EstadoCuenta estado;
    private CodigoValidacion codigoValidacionRegistro;
    private CodigoValidacion codigoValidacionPassword;

    @Builder
    public Cuenta(String correo, String password, Rol rol, LocalDateTime fechaRegistro, LocalDateTime fechaNacimiento, Usuario usuario, EstadoCuenta estado, CodigoValidacion codigoValidacionRegistro, CodigoValidacion codigoValidacionPassword){

        this.correo=correo;
        this.password=password;
        this.rol=rol;
        this.fechaRegistro=fechaRegistro;
        this.fechaNacimiento=fechaNacimiento;
        this.usuario=usuario;
        this.estado=estado;
        this.codigoValidacionRegistro=codigoValidacionRegistro;
        this.codigoValidacionPassword=codigoValidacionPassword;

    }
}
