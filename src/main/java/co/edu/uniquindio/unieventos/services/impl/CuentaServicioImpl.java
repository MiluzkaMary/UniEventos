package co.edu.uniquindio.unieventos.services.impl;

import co.edu.uniquindio.unieventos.dto.cuenta.CrearCuentaDTO;
import co.edu.uniquindio.unieventos.dto.cuenta.EditarCuentaDTO;
import co.edu.uniquindio.unieventos.dto.cuenta.InformacionCuentaDTO;
import co.edu.uniquindio.unieventos.dto.cuenta.ItemCuentaDTO;
import co.edu.uniquindio.unieventos.model.documents.Cuenta;
import co.edu.uniquindio.unieventos.model.enums.EstadoCuenta;
import co.edu.uniquindio.unieventos.model.enums.Rol;
import co.edu.uniquindio.unieventos.model.vo.Usuario;
import co.edu.uniquindio.unieventos.repositories.CuentaRepo;
import co.edu.uniquindio.unieventos.services.interfaces.CuentaServicio;
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

    @Override
    public String crearCuenta(CrearCuentaDTO cuenta) throws Exception {

        if (existeEmail(cuenta.correo())) {
            throw new Exception("El correo " + cuenta.correo() + " ya está en uso");
        }

        if (existeCedula(cuenta.cedula())) {
            throw new Exception("La cédula " + cuenta.cedula() + " ya se encuentra registrada");
        }

        // Mapeamos (pasamos) los datos del DTO a un objeto de tipo Cuenta
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setCorreo(cuenta.correo());
        nuevaCuenta.setPassword(cuenta.password());
        nuevaCuenta.setRol(Rol.CLIENTE);
        nuevaCuenta.setFechaRegistro(LocalDateTime.now());
        nuevaCuenta.setUsuario(new Usuario(
                cuenta.cedula(),
                cuenta.nombre(),
                cuenta.telefono(),
                cuenta.direccion()));
        nuevaCuenta.setEstado(EstadoCuenta.INACTIVO);

        // Guardamos la cuenta del usuario en la base de datos
        Cuenta cuentaCreada = cuentaRepo.save(nuevaCuenta);
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
                cuenta.cedula(),
                cuenta.nombre(),
                cuenta.telefono(),
                cuenta.direccion()));

        // Guardar la cuenta actualizada en la base de datos
        cuentaRepo.save(cuentaExistente);
    }

    @Override
    public void eliminarCuenta(String id) throws Exception {
        // Buscar la cuenta existente por ID
        Cuenta cuentaExistente = cuentaRepo.findById(id)
                .orElseThrow(() -> new Exception("Cuenta no encontrada"));

        // Asignar el estado ELIMINADO a la cuenta
        cuentaExistente.setEstado(EstadoCuenta.ELIMINADO);

        // Guardar los cambios en la base de datos (actualiza el registro existente)
        cuentaRepo.save(cuentaExistente);
    }

    @Override
    public InformacionCuentaDTO obtenerInformacionCuenta(String id) throws Exception {
        // Buscar la cuenta existente por ID
        Cuenta cuentaExistente = cuentaRepo.findById(id)
                .orElseThrow(() -> new Exception("Cuenta no encontrada"));

        // Mapear los datos de la cuenta a un DTO de información de cuenta
        InformacionCuentaDTO informacionCuenta = new InformacionCuentaDTO(
                cuentaExistente.getId(), cuentaExistente.getCorreo(), cuentaExistente.getUsuario().getCedula(),
                cuentaExistente.getUsuario().getNombre(), cuentaExistente.getUsuario().getDireccion(),
                cuentaExistente.getUsuario().getTelefono(), cuentaExistente.getFechaNacimiento(),
                cuentaExistente.getEstado());

        return informacionCuenta;
    }

    @Override
    public List<ItemCuentaDTO> listarCuentas() {
        // Obtener todas las cuentas de la base de datos
        List<Cuenta> cuentas = cuentaRepo.findAll();

        // Mapear las cuentas a una lista de ItemCuentaDTO
        List<ItemCuentaDTO> cuentasDTO = cuentas.stream()
                .map(cuenta -> new ItemCuentaDTO(
                        cuenta.getId(),
                        cuenta.getCorreo(),
                        cuenta.getUsuario().getNombre(),
                        cuenta.getUsuario().getCedula(),
                        cuenta.getUsuario().getTelefono()))
                .toList();

        return cuentasDTO;
    }




    private boolean existeEmail(String email) {
        return cuentaRepo.findByEmail(email).isPresent();
    }

    private boolean existeCedula(String cedula) {
        return cuentaRepo.findByCedula(cedula).isPresent();
    }
}
