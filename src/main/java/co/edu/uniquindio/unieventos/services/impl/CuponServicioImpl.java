package co.edu.uniquindio.unieventos.services.impl;

import co.edu.uniquindio.unieventos.dto.cupon.CrearCuponDTO;
import co.edu.uniquindio.unieventos.dto.cupon.EditarCuponDTO;
import co.edu.uniquindio.unieventos.dto.cupon.InformacionCuponDTO;
import co.edu.uniquindio.unieventos.dto.cupon.ItemCuponDTO;
import co.edu.uniquindio.unieventos.model.documents.Cupon;
import co.edu.uniquindio.unieventos.model.enums.EstadoCuenta;
import co.edu.uniquindio.unieventos.model.enums.EstadoCupon;
import co.edu.uniquindio.unieventos.repositories.CuponRepo;
import co.edu.uniquindio.unieventos.services.interfaces.CuponServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class CuponServicioImpl implements CuponServicio {

    private final CuponRepo cuponRepo;

    @Override
    public String crearCupon(CrearCuponDTO cuponDTO) throws Exception {

        // Verificar si ya existe un cupón con el mismo código
        if (existeCodigo(cuponDTO.codigo())) {
            throw new Exception("El código del cupón ya está en uso: " + cuponDTO.codigo());
        }

        // Verificar si ya existe un cupón con el mismo nombre
        if (existeNombre(cuponDTO.nombre())) {
            throw new Exception("El nombre del cupón ya está en uso: " + cuponDTO.nombre());
        }

        // Mapeamos los datos del DTO a un objeto de tipo Cupon
        Cupon nuevoCupon = Cupon.builder()
                .codigo(cuponDTO.codigo())
                .nombre(cuponDTO.nombre())
                .descuento(cuponDTO.descuento())
                .fechaVencimiento(cuponDTO.fechaVencimiento())
                .estado(EstadoCupon.DISPONIBLE)  // Estado DISPONIBLE por defecto
                .tipo(cuponDTO.tipo())
                .build();

        // Guardamos el cupón en la base de datos
        Cupon cuponCreado = cuponRepo.save(nuevoCupon);
        return cuponCreado.getId();
    }

    @Override
    public void editarCupon(EditarCuponDTO cuponDTO) throws Exception {
        // Buscar el cupón existente por ID
        Cupon cuponExistente = cuponRepo.findById(cuponDTO.id())
                .orElseThrow(() -> new Exception("Cupón no encontrado"));

        // Actualizar los datos del cupón existente con los datos del DTO
        cuponExistente.setCodigo(cuponDTO.codigo());
        cuponExistente.setNombre(cuponDTO.nombre());
        cuponExistente.setDescuento(cuponDTO.descuento());
        cuponExistente.setFechaVencimiento(cuponDTO.fechaVencimiento());
        cuponExistente.setEstado(cuponDTO.estado());
        cuponExistente.setTipo(cuponDTO.tipo());

        // Guardar el cupón actualizado en la base de datos
        cuponRepo.save(cuponExistente);
    }

    @Override
    public void eliminarCupon(String cuponId) throws Exception {
        // Buscar el cupón existente por ID
        Cupon cuponExistente = cuponRepo.findById(cuponId)
                .orElseThrow(() -> new Exception("Cupón no encontrado"));

        // Cambiar el estado del cupón a NO_DISPONIBLE (no se elimina físicamente)
        cuponExistente.setEstado(EstadoCupon.NO_DISPONIBLE);

        // Guardar el cambio en la base de datos
        cuponRepo.save(cuponExistente);
    }

    @Override
    public InformacionCuponDTO obtenerCupon(String cuponId) throws Exception {
        // Buscar el cupón existente por ID
        Cupon cuponExistente = cuponRepo.findById(cuponId)
                .orElseThrow(() -> new Exception("Cupón no encontrado"));

        if (cuponExistente.getEstado() == EstadoCupon.NO_DISPONIBLE) {
            throw new Exception("Cupon Eliminado");
        }

        // Mapear los datos del cupón a un DTO de información de cupón
        return new InformacionCuponDTO(
                cuponExistente.getId(),
                cuponExistente.getCodigo(),
                cuponExistente.getNombre(),
                cuponExistente.getDescuento(),
                cuponExistente.getFechaVencimiento(),
                cuponExistente.getEstado(),
                cuponExistente.getTipo());
    }

    @Override
    public List<ItemCuponDTO> obtenerTodosLosCupones() {
        // Obtener todos los cupones de la base de datos
        List<Cupon> cupones = cuponRepo.findAll();

        // Mapear los cupones a una lista de ItemCuponDTO
        return cupones.stream()
                .map(cupon -> new ItemCuponDTO(
                        cupon.getId(),
                        cupon.getNombre(),
                        cupon.getDescuento(),
                        cupon.getFechaVencimiento(),
                        cupon.getTipo()))
                .toList();
    }

    private boolean existeCodigo(String codigo) {
        return cuponRepo.findByCodigo(codigo).isPresent();
    }

    private boolean existeNombre(String nombre) {
        return cuponRepo.findByNombre(nombre).isPresent();
    }
}
