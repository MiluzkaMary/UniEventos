package co.edu.uniquindio.unieventos.services.impl;

import co.edu.uniquindio.unieventos.dto.orden.*;
import co.edu.uniquindio.unieventos.model.documents.Orden;
import co.edu.uniquindio.unieventos.model.vo.DetalleOrden;
import co.edu.uniquindio.unieventos.model.vo.Pago;
import co.edu.uniquindio.unieventos.repositories.OrdenRepo;
import co.edu.uniquindio.unieventos.services.interfaces.OrdenServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrdenServicioImpl implements OrdenServicio {

    private final OrdenRepo ordenRepo;

    @Override
    public String crearOrden(CrearOrdenDTO ordenDTO) throws Exception {
        // Verificar si ya existe una orden para la cuenta
        if (existeOrdenPorCuentaId(ordenDTO.idCuenta())) {
            throw new Exception("Ya existe una orden para esta cuenta.");
        }

        // Verificar si ya existe una orden con el mismo código QR
        if (existeOrdenPorCodigoQR(ordenDTO.codigoQR())) {
            throw new Exception("El código QR " + ordenDTO.codigoQR() + " ya está en uso.");
        }

        // Crear una nueva orden
        Orden nuevaOrden = Orden.builder()
                .idCuenta(ordenDTO.idCuenta())  // Asignar ID de cuenta
                .idCupon(ordenDTO.idCupon() != null ? ordenDTO.idCupon() : null) // Asignar ID de cupón si existe
                .pago(ordenDTO.pago())          // Mapear pago desde DTO
                .fecha(ordenDTO.fecha() != null ? ordenDTO.fecha() : LocalDateTime.now()) // Fecha actual o proporcionada
                .codigoQR(ordenDTO.codigoQR())  // Código QR de la orden
                .items(ordenDTO.items().stream() // Mapear los detalles a la entidad DetalleOrden
                        .map(detalle -> new DetalleOrden(
                                detalle.idProducto(),           // ID del producto o evento
                                detalle.idEvento(),           // ID del evento
                                detalle.cantidad(),             // Cantidad de productos
                                detalle.nombreLocalidad(),       // Nombre de la localidad o producto
                                detalle.precioUnitario(),       // Precio unitario
                                detalle.total()))               // Total del detalle
                        .toList())               // Convertir a lista de DetalleOrden
                .total(ordenDTO.total())         // Asignar el total de la orden
                .build();

        // Guardar la orden en la base de datos
        Orden ordenCreada = ordenRepo.save(nuevaOrden);
        return ordenCreada.getId(); // Retornar el ID de la orden creada
    }


    @Override
    public void editarOrden(EditarOrdenDTO ordenDTO) throws Exception {
        // Buscar la orden existente por ID
        Orden ordenExistente = ordenRepo.findById(ordenDTO.id())
                .orElseThrow(() -> new Exception("Orden no encontrada"));

        // Actualizar los atributos de la orden existente
        ordenExistente.setIdCuenta(ordenDTO.idCuenta());
        ordenExistente.setIdCupon(ordenDTO.idCupon() != null ? ordenDTO.idCupon() : null);
        ordenExistente.setPago(ordenDTO.pago());
        ordenExistente.setFecha(ordenDTO.fecha());
        ordenExistente.setCodigoQR(ordenDTO.codigoQR());
        ordenExistente.setItems(ordenDTO.items().stream()
                .map(detalle -> new DetalleOrden(
                        detalle.idProducto(),       // ID del producto o evento
                        detalle.idEvento(),       // ID del evento
                        detalle.cantidad(),         // Cantidad de productos
                        detalle.nombreLocalidad(),   // Nombre del producto o localidad
                        detalle.precioUnitario(),   // Precio unitario
                        detalle.total()))           // Total del detalle
                .toList());
        ordenExistente.setTotal(ordenDTO.total());

        // Guardar la orden actualizada en la base de datos
        ordenRepo.save(ordenExistente);
    }

    @Override
    public void eliminarOrden(String id) throws Exception {
        // Buscar la orden existente por ID
        Orden ordenExistente = ordenRepo.findById(id)
                .orElseThrow(() -> new Exception("Orden no encontrada"));

        // Eliminar la orden de la base de datos
        ordenRepo.delete(ordenExistente);
    }

    @Override
    public InformacionOrdenDTO obtenerInformacionOrden(String id) throws Exception {
        // Buscar la orden existente por ID
        Orden ordenExistente = ordenRepo.findById(id)
                .orElseThrow(() -> new Exception("Orden no encontrada"));

        // Mapear la orden a InformacionOrdenDTO
        return new InformacionOrdenDTO(
                ordenExistente.getId(),
                ordenExistente.getIdCuenta(),
                ordenExistente.getIdCupon(),
                ordenExistente.getPago(),
                ordenExistente.getFecha(),
                ordenExistente.getCodigoQR(),
                ordenExistente.getItems().stream()
                        .map(detalle -> new DetalleOrden(
                                detalle.getId(),
                                detalle.getIdEvento(),
                                detalle.getCantidad(),
                                detalle.getNombreLocalidad(),
                                detalle.getPrecio(),
                                detalle.getPrecioUnitario() ))
                        .toList(),
                ordenExistente.getTotal()
        );
    }

    @Override
    public List<ItemOrdenDTO> listarOrdenes() {
        // Obtener todas las órdenes de la base de datos
        List<Orden> ordenes = ordenRepo.findAll();

        // Mapear las órdenes a una lista de ItemOrdenDTO
        return ordenes.stream()
                .map(orden -> new ItemOrdenDTO(
                        orden.getId(),                           // ID de la orden
                        orden.getIdCuenta(),                     // ID de la cuenta
                        orden.getIdCupon(),                      // ID del cupón
                        orden.getFecha(),                        // Fecha de la orden
                        orden.getItems().stream()                // Mapeo de los detalles de la orden a DetalleOrdenDTO
                                .map(detalle -> new DetalleOrdenDTO(
                                        detalle.getId(),          // ID del detalle
                                        detalle.getIdEvento(),
                                        detalle.getNombreLocalidad(),// ID del evento
                                        detalle.getCantidad(),    // Cantidad del producto
                                        detalle.getPrecioUnitario(), // Precio unitario
                                        detalle.getPrecio())) // Total calculado
                                .toList(),                       // Convertir a lista de DetalleOrdenDTO
                        orden.getTotal()                         // Total de la orden
                ))
                .toList();  // Convertir el resultado final a una lista de ItemOrdenDTO
    }

    private boolean existeOrdenPorCuentaId(ObjectId idCuenta) {
        return ordenRepo.findByIdCuenta(idCuenta).isPresent();
    }

    private boolean existeOrdenPorCodigoQR(String codigoQR) {
        return ordenRepo.findByCodigoQR(codigoQR).isPresent();
    }


}
