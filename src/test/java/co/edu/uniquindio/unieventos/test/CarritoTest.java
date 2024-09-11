package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.model.documents.Carrito;
import co.edu.uniquindio.unieventos.model.vo.DetalleCarrito;
import co.edu.uniquindio.unieventos.repositories.CarritoRepo;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CarritoTest {

    @Autowired
    private CarritoRepo carritoRepo;

    @Test
    public void registrarTest() {
        Carrito carrito = Carrito.builder()
                .idCuenta(new ObjectId("64e1fd35a4237a361b70250e"))
                .fecha(LocalDateTime.now())
                .items(Collections.singletonList(
                        DetalleCarrito.builder()
                                .idEvento(new ObjectId("66e2047ffe1c6311babc5b8c"))
                                .cantidad(2)
                                .nombreLocalidad("General")
                                .build()
                ))
                .build();

        // Guardar el carrito
        Carrito carritoCreado = carritoRepo.save(carrito);

        // Verificar que el carrito no sea null
        assertNotNull(carritoCreado);
    }

    @Test
    public void actualizarTest() {
        // Obtener carrito por id
        Carrito carrito = carritoRepo.findById("XXX").orElseThrow();
        // Modificar fecha del carrito
        carrito.setFecha(LocalDateTime.now().plusDays(1));

        // Guardar
        carritoRepo.save(carrito);

        // Obtener el carrito actualizado
        Carrito carritoActualizado = carritoRepo.findById("XXX").orElseThrow();

        // Verificar actualización
        assertEquals(LocalDateTime.now().plusDays(1).toLocalDate(), carritoActualizado.getFecha().toLocalDate());
    }

    @Test
    public void listarTodosTest() {
        List<Carrito> lista = carritoRepo.findAll();

        lista.forEach(System.out::println);

        // Verificar que la lista no esté vacía
        assertFalse(lista.isEmpty());
    }

    @Test
    public void eliminarTest() {
        // Borrar carrito con id
        carritoRepo.deleteById("XXX");

        // Obtener el carrito eliminado
        Carrito carrito = carritoRepo.findById("XXX").orElse(null);

        // Verificar que es null
        assertNull(carrito);
    }
}
