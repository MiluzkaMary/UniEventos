package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.dto.carrito.*;
import co.edu.uniquindio.unieventos.services.interfaces.CarritoServicio;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CarritoServicioTest {

    @Autowired
    private CarritoServicio carritoServicio;

    @Test
    public void crearCarritoTest() {
        // Definir el ID de la cuenta y los detalles del carrito
        String idCuenta = "6708dc06b9ac5729811ed9e6"; //Jimena Cardona
        DetalleCarritoDTO detalle1 = new DetalleCarritoDTO(new ObjectId("670888b687c495460cab2526"), 2, "General"); //Feria Libro
        DetalleCarritoDTO detalle2 = new DetalleCarritoDTO(new ObjectId("670888c660b34ff4c9777915"), 1, "VIP"); //Festival Electronica

        // Crear un DTO para el carrito con los detalles
        CrearCarritoDTO crearCarritoDTO = new CrearCarritoDTO(
                new ObjectId(idCuenta),
                LocalDateTime.now(),
                List.of(detalle1, detalle2)
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow(() -> {
            // Se crea el carrito y se imprime el ID
            String idCarrito = carritoServicio.crearCarrito(crearCarritoDTO);
            // Se espera que el ID no sea nulo
            assertNotNull(idCarrito);
        });
    }

    @Test
    public void editarCarritoTest() {
        // Definir el ID del carrito a editar
        String idCarrito = "6708f4b145fd3f3d716f9cfc";
        DetalleCarritoDTO detalle1 = new DetalleCarritoDTO(new ObjectId("670888ce3460da95cc69df75"), 3, "Platino"); //Competencia Sports
        DetalleCarritoDTO detalle2 = new DetalleCarritoDTO(new ObjectId("670888c660b34ff4c9777915"), 2, "General"); //Festival Electronica

        // Crear un DTO para editar el carrito
        EditarCarritoDTO editarCarritoDTO = new EditarCarritoDTO(
                idCarrito,
                new ObjectId("6708dc06b9ac5729811ed9e6"),
                LocalDateTime.now(),
                List.of(detalle1, detalle2)
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow(() -> {

            // Se actualiza el carrito con los nuevos detalles
            carritoServicio.editarCarrito(editarCarritoDTO);

            // Obtenemos el detalle del carrito actualizado y verificamos los cambios
            InformacionCarritoDTO detalleCarrito = carritoServicio.obtenerCarritoPorCuenta(new ObjectId("6708dc06b9ac5729811ed9e6"));
            assertNotNull(detalleCarrito);
            assertEquals(2, detalleCarrito.items().size());
            assertEquals(new ObjectId("670888ce3460da95cc69df75"), detalleCarrito.items().get(0).getIdEvento());
            System.out.println("En este carrito hay "+detalleCarrito.items().get(0).getCantidad()+" entradas "+detalleCarrito.items().get(0).getNombreLocalidad() +" para el 1ER evento ");
        });
    }

    @Test
    public void eliminarCarritoTest() {
        // Definir el ID del carrito a eliminar
        String idCarrito = "6708f4b145fd3f3d716f9cfc";

        // Se espera que no se lance ninguna excepción al eliminar el carrito
        assertDoesNotThrow(() -> carritoServicio.eliminarCarrito(idCarrito));

        // Se espera que se lance una excepción al intentar obtener el carrito eliminado
        assertThrows(Exception.class, () -> carritoServicio.obtenerCarritoPorCuenta(new ObjectId("65234abcd123ef1234abcd14")));
    }

    @Test
    public void listarCarritosTest() {
        // Obtener la lista de todos los carritos
        List<ItemCarritoDTO> listaCarritos = carritoServicio.listarCarritos();

        listaCarritos.forEach(carr -> System.out.println("Carrito: " + carr));

        // Verificar que la lista no sea nula y contenga al menos un carrito
        assertNotNull(listaCarritos);
        assertTrue(listaCarritos.size() > 0);
    }
}
