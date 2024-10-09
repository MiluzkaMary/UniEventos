package co.edu.uniquindio.unieventos.services.impl;

import co.edu.uniquindio.unieventos.model.documents.Carrito;
import co.edu.uniquindio.unieventos.model.vo.DetalleCarrito;
import co.edu.uniquindio.unieventos.repositories.CarritoRepo;
import co.edu.uniquindio.unieventos.services.interfaces.CarritoServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CarritoServicioImpl implements CarritoServicio {

    private final CarritoRepo carritoRepo;

    @Override
    public Carrito agregarItem(String carritoId, DetalleCarrito detalleCarrito) {
        Optional<Carrito> carritoOpt = carritoRepo.findById(carritoId);
        if (carritoOpt.isEmpty()) {
            throw new RuntimeException("Carrito no encontrado");
        }
        Carrito carrito = carritoOpt.get();
        carrito.getItems().add(detalleCarrito);
        return carritoRepo.save(carrito);
    }

    @Override
    public Carrito eliminarItem(String carritoId, String itemId) {
        Optional<Carrito> carritoOpt = carritoRepo.findById(carritoId);
        if (carritoOpt.isEmpty()) {
            throw new RuntimeException("Carrito no encontrado");
        }
        Carrito carrito = carritoOpt.get();
        carrito.getItems().removeIf(item -> item.getIdEvento().toString().equals(itemId));
        return carritoRepo.save(carrito);
    }

    @Override
    public Carrito obtenerCarrito(String carritoId) {
        return carritoRepo.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
    }

    @Override
    public Carrito vaciarCarrito(String carritoId) {
        Optional<Carrito> carritoOpt = carritoRepo.findById(carritoId);
        if (carritoOpt.isEmpty()) {
            throw new RuntimeException("Carrito no encontrado");
        }
        Carrito carrito = carritoOpt.get();
        carrito.getItems().clear();
        return carritoRepo.save(carrito);
    }

    @Override
    public List<DetalleCarrito> obtenerItems(String carritoId) {
        Carrito carrito = carritoRepo.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        return carrito.getItems();
    }
}
