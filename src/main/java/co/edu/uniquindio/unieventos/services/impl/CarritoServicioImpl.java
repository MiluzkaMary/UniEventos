package co.edu.uniquindio.unieventos.services.impl;

import co.edu.uniquindio.unieventos.dto.carrito.*;
import co.edu.uniquindio.unieventos.model.documents.Carrito;
import co.edu.uniquindio.unieventos.model.vo.DetalleCarrito;
import co.edu.uniquindio.unieventos.repositories.CarritoRepo;
import co.edu.uniquindio.unieventos.services.interfaces.CarritoServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CarritoServicioImpl implements CarritoServicio {

    private final CarritoRepo carritoRepo;

    @Override
    public String crearCarrito(CrearCarritoDTO carritoDTO) throws Exception {
        // Verificar si ya existe un carrito para la cuenta
        if (existeCarritoPorCuenta(new ObjectId(carritoDTO.idCuenta()))) {
            throw new Exception("Ya existe un carrito para esta cuenta.");
        }

        // Crear un nuevo carrito utilizando DetalleCarritoDTO
        Carrito nuevoCarrito = Carrito.builder()
                .idCuenta(new ObjectId(carritoDTO.idCuenta()))  // Asignar ID de cuenta
                .fecha(carritoDTO.fecha() != null ? carritoDTO.fecha() : LocalDateTime.now()) // Fecha actual o proporcionada
                .items(carritoDTO.items().stream()
                        .map(detalle -> new DetalleCarrito(
                                detalle.idEvento(),           // ID del evento del DetalleCarritoDTO
                                detalle.cantidad(),           // Cantidad del DetalleCarritoDTO
                                detalle.nombreLocalidad()))   // Nombre de la localidad del DetalleCarritoDTO
                        .toList()) // Mapear los detalles a la entidad
                .build();

        // Guardar el carrito en la base de datos
        Carrito carritoCreado = carritoRepo.save(nuevoCarrito);
        return carritoCreado.getId(); // Retornar el ID del carrito creado
    }



    @Override
    public void editarCarrito(EditarCarritoDTO carritoDTO) throws Exception {
        // Buscar el carrito existente por ID
        Carrito carritoExistente = carritoRepo.findById(carritoDTO.id())
                .orElseThrow(() -> new Exception("Carrito no encontrado"));

        // Actualizar los atributos del carrito existente
        carritoExistente.setIdCuenta(carritoDTO.idCuenta());
        carritoExistente.setFecha(carritoDTO.fecha());
        carritoExistente.setItems(carritoDTO.items().stream()
                .map(detalle -> new DetalleCarrito(
                        detalle.idEvento(),
                        detalle.cantidad(),
                        detalle.nombreLocalidad()))
                .toList());

        // Guardar el carrito actualizado en la base de datos
        carritoRepo.save(carritoExistente);
    }

    @Override
    public void eliminarCarrito(String id) throws Exception {
        // Buscar el carrito existente por ID
        Carrito carritoExistente = carritoRepo.findById(id)
                .orElseThrow(() -> new Exception("Carrito no encontrado"));

        // Eliminar el carrito de la base de datos
        carritoRepo.delete(carritoExistente);
    }

    @Override
    public InformacionCarritoDTO obtenerCarritoPorCuenta(ObjectId idCuenta) throws Exception {
        // Buscar el carrito asociado a la cuenta
        Carrito carritoExistente = carritoRepo.findByIdCuenta(idCuenta)
                .orElseThrow(() -> new Exception("Carrito no encontrado para la cuenta"));

        // Mapear el carrito a InformacionCarritoDTO
        return new InformacionCarritoDTO(
                carritoExistente.getId(),
                carritoExistente.getIdCuenta(),
                carritoExistente.getFecha(),
                carritoExistente.getItems().stream()
                        .map(detalle -> new DetalleCarrito(
                                detalle.getIdEvento(),
                                detalle.getCantidad(),
                                detalle.getNombreLocalidad()))
                        .toList()
        );
    }

    @Override
    public List<ItemCarritoDTO> listarCarritos() {
        // Obtener todos los carritos de la base de datos
        List<Carrito> carritos = carritoRepo.findAll();

        // Mapear los carritos a una lista de ItemCarritoDTO
        return carritos.stream()
                .map(carrito -> new ItemCarritoDTO(
                        carrito.getId(),                           // ID del carrito
                        carrito.getIdCuenta(),                     // ID de la cuenta
                        carrito.getFecha(),                        // Fecha del carrito
                        carrito.getItems().stream()                // Mapeo de los detalles del carrito a DetalleCarritoDTO
                                .map(detalle -> new DetalleCarritoDTO(
                                        detalle.getIdEvento(),
                                        detalle.getCantidad(),
                                        detalle.getNombreLocalidad()))
                                .toList()                              // Convertir a lista de DetalleCarritoDTO
                ))
                .toList();  // Convertir el resultado final a una lista de ItemCarritoDTO
    }


    private boolean existeCarritoPorCuenta(ObjectId idCuenta) {
        return carritoRepo.findByIdCuenta(idCuenta).isPresent();
    }

}
