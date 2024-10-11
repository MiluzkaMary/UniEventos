package co.edu.uniquindio.unieventos.services.impl;

import co.edu.uniquindio.unieventos.dto.cuenta.CrearCuentaDTO;
import co.edu.uniquindio.unieventos.dto.cuenta.EditarCuentaDTO;
import co.edu.uniquindio.unieventos.dto.cuenta.InformacionCuentaDTO;
import co.edu.uniquindio.unieventos.dto.cuenta.UsuarioDTO;
import co.edu.uniquindio.unieventos.dto.cuenta.ItemCuentaDTO;
import co.edu.uniquindio.unieventos.dto.otros.EmailDTO;
import co.edu.uniquindio.unieventos.model.documents.Cuenta;
import co.edu.uniquindio.unieventos.model.enums.EstadoCuenta;
import co.edu.uniquindio.unieventos.model.enums.Rol;
import co.edu.uniquindio.unieventos.model.vo.Usuario;
import co.edu.uniquindio.unieventos.repositories.CuentaRepo;
import co.edu.uniquindio.unieventos.services.interfaces.CuentaServicio;
import co.edu.uniquindio.unieventos.services.interfaces.EmailServicio;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CuentaServicioImpl implements CuentaServicio {

    private final CuentaRepo cuentaRepo;

    private final EmailServicio emailServicio;

    @Override
    public String crearCuenta(CrearCuentaDTO cuenta) throws Exception {

        // Verificar si el correo ya existe
        if (existeEmail(cuenta.correo())) {
            throw new Exception("El correo " + cuenta.correo() + " ya está en uso");
        }

        // Convertir UsuarioDTO a Usuario antes de verificar si el usuario existe
        Usuario usuario = new Usuario(
                cuenta.usuario().cedula(),
                cuenta.usuario().nombre(),
                cuenta.usuario().telefono(),
                cuenta.usuario().direccion()
        );

        // Verificar si el usuario ya existe utilizando el objeto Usuario
        if (existeUsuario(usuario)) {
            throw new Exception("La cédula " + cuenta.usuario().cedula() + " ya se encuentra registrada");
        }

        // Crear una nueva cuenta
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setCorreo(cuenta.correo());
        nuevaCuenta.setPassword(cuenta.password());
        nuevaCuenta.setRol(Rol.CLIENTE);
        nuevaCuenta.setFechaRegistro(LocalDateTime.now());
        nuevaCuenta.setUsuario(usuario);  // Asignar el usuario a la cuenta
        nuevaCuenta.setEstado(EstadoCuenta.ACTIVO);  // Estado ACTIVO por defecto

        // Guardar la cuenta en la base de datos
        Cuenta cuentaCreada = cuentaRepo.save(nuevaCuenta);


        //BIENVENIDA
        EmailDTO email = new EmailDTO(
                "¡Bienvenido a UniEventos "+cuenta.usuario().nombre()+"!",
                "Hola " + cuenta.usuario().nombre() + ", ¡Tu información personal fue guardada en nuestra base de datos, ten una calida bienvenida a AVT!",
                cuenta.correo()
        );

        emailServicio.enviarCorreo(email);

        //CODIGO DE VERIFICACION
        email=new EmailDTO(
                "Codigo de Verificacion de cuenta",
                "Enviamos el codigo para poder verificar su cuenta"+"\n Codigo:  "+emailServicio.generarCodigoCuenta(),
                cuenta.correo()
        );

        emailServicio.enviarCorreo(email);

        return cuentaCreada.getId();
    }


    @Override
    public void editarCuenta( EditarCuentaDTO cuenta) throws Exception {
        // Buscar la cuenta existente por ID
        Cuenta cuentaExistente = cuentaRepo.findById(cuenta.id())
                .orElseThrow(() -> new Exception("Cuenta no encontrada"));

        // Actualizar los datos de la cuenta existente con los datos del DTO
        cuentaExistente.setCorreo(cuenta.correo());
        cuentaExistente.setPassword(cuenta.password());
        cuentaExistente.setUsuario(new Usuario(
                cuenta.usuario().cedula(),
                cuenta.usuario().nombre(),
                cuenta.usuario().telefono(),
                cuenta.usuario().direccion()));
        cuentaExistente.setEstado(cuenta.estado());

        // Guardar la cuenta actualizada en la base de datos
        cuentaRepo.save(cuentaExistente);

        //NOTIFICACION
        EmailDTO email = new EmailDTO(
                "Modificacion de datos",
                "Hola " + cuenta.usuario().nombre() + ", Tu información personal fue editada en nuestra base de datos",
                cuenta.correo()
        );

        emailServicio.enviarCorreo(email);

    }

    @Override
    public void eliminarCuenta(String id) throws Exception {
        // Buscar la cuenta existente por ID
        Cuenta cuentaExistente = cuentaRepo.findById(id)
                .orElseThrow(() -> new Exception("Cuenta no encontrada"));

        // Asignar el estado ELIMINADO a la cuenta
        cuentaExistente.setEstado(EstadoCuenta.ELIMINADO);

        //NOTIFICACION
        EmailDTO email = new EmailDTO(
                "Eliminacion de cuenta",
                "Hola " + cuentaExistente.getUsuario().getNombre() + ", Te informamos que hemos eliminado tu cuenta",
                cuentaExistente.getCorreo()
        );
        emailServicio.enviarCorreo(email);
        // Guardar los cambios en la base de datos (actualiza el registro existente)
        cuentaRepo.save(cuentaExistente);


    }

    @Override
    public InformacionCuentaDTO obtenerInformacionCuenta(String id) throws Exception {
        // Buscar la cuenta existente por ID
        Cuenta cuentaExistente = cuentaRepo.findById(id)
                .orElseThrow(() -> new Exception("Cuenta no encontrada"));

        // Verificar si la cuenta tiene el estado ELIMINADO
        if (cuentaExistente.getEstado() == EstadoCuenta.ELIMINADO) {
            System.out.println("Cuenta eliminada, lanzando excepción...");
            throw new Exception("Cuenta Eliminada");

        }

        // Mapear los datos de la cuenta a un DTO de información de cuenta
        InformacionCuentaDTO informacionCuenta = new InformacionCuentaDTO(
                cuentaExistente.getId(),
                cuentaExistente.getCorreo(),
                cuentaExistente.getUsuario(),
                cuentaExistente.getEstado());

        return informacionCuenta;
    }


    @Override
    public List<ItemCuentaDTO> listarCuentas() {
        // Obtener todas las cuentas de la base de datos
        List<Cuenta> cuentas = cuentaRepo.findAll();

        // Mapear las cuentas a una lista de ItemCuentaDTO
        return cuentas.stream()
                .map(cuenta -> new ItemCuentaDTO(
                        cuenta.getId(),
                        cuenta.getCorreo(),
                        new UsuarioDTO(
                                cuenta.getUsuario().getCedula(),
                                cuenta.getUsuario().getNombre(),
                                cuenta.getUsuario().getTelefono(),
                                cuenta.getUsuario().getDireccion()
                        ),
                        cuenta.getEstado()
                ))
                .toList();  // Convertir a lista de ItemCuentaDTO
    }





    private boolean existeEmail(String correo) {
        return cuentaRepo.findByCorreo(correo).isPresent();
    }

    private boolean existeUsuario(Usuario usuario) {
        return cuentaRepo.findByUsuario(usuario).isPresent();
    }
}
