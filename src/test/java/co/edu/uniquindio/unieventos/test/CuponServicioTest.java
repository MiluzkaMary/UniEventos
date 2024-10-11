package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.dto.cupon.CrearCuponDTO;
import co.edu.uniquindio.unieventos.dto.cupon.EditarCuponDTO;
import co.edu.uniquindio.unieventos.dto.cupon.InformacionCuponDTO;
import co.edu.uniquindio.unieventos.dto.cupon.ItemCuponDTO;
import co.edu.uniquindio.unieventos.model.enums.EstadoCupon;
import co.edu.uniquindio.unieventos.model.enums.TipoCupon;
import co.edu.uniquindio.unieventos.services.interfaces.CuponServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CuponServicioTest {

    @Autowired
    private CuponServicio cuponServicio;

    @Test
    public void crearCuponTest() {
        // Crear un DTO con los datos del cupón
        CrearCuponDTO cuponDTO = new CrearCuponDTO(
                "DESC20",               // Código del cupón
                "Descuento 20%",        // Nombre del cupón
                20.0,                   // Descuento del cupón
                LocalDateTime.now().plusDays(10),
                EstadoCupon.DISPONIBLE,// Fecha de vencimiento
                TipoCupon.MULTIPLE    // Tipo de cupón
        );

        // Se espera que no se lance ninguna excepción al crear el cupón
        assertDoesNotThrow(() -> {
            // Crear el cupón y obtener su ID
            String cuponId = cuponServicio.crearCupon(cuponDTO);
            assertNotNull(cuponId); // Verificar que el ID del cupón no sea nulo
        });
    }

    @Test
    public void editarCuponTest() {
        // Definir un ID de cupón ficticio
        String cuponId = "6708f8804e59754d21cdfdb4";

        // Crear un DTO con los nuevos datos para editar el cupón
        EditarCuponDTO editarCuponDTO = new EditarCuponDTO(
                cuponId,
                "DESC30",               // Nuevo código del cupón
                "Descuento 30%",        // Nuevo nombre del cupón
                30.0,                   // Nuevo descuento
                LocalDateTime.now().plusDays(15), // Nueva fecha de vencimiento
                EstadoCupon.DISPONIBLE, // Estado disponible
                TipoCupon.UNICO          // Cambiar el tipo de cupón a FIJO
        );

        // Se espera que no se lance ninguna excepción al editar el cupón
        assertDoesNotThrow(() -> {
            // Editar el cupón
            cuponServicio.editarCupon(editarCuponDTO);

            // Verificar que los cambios se hayan aplicado correctamente
            InformacionCuponDTO cuponActualizado = cuponServicio.obtenerCupon(cuponId);
            assertEquals("DESC30", cuponActualizado.codigo());
            assertEquals("Descuento 30%", cuponActualizado.nombre());
            assertEquals(30.0, cuponActualizado.descuento());
        });
    }

    @Test
    public void eliminarCuponTest() {
        // Definir un ID de cupón ficticio
        String cuponId = "6708f8804e59754d21cdfdb4";

        // Se espera que no se lance ninguna excepción al eliminar el cupón
        assertDoesNotThrow(() ->
            // Eliminar el cupón
            cuponServicio.eliminarCupon(cuponId));


        //Al intentar obtener el cupon se debe lanzar una excepción
        assertThrows(Exception.class, () -> cuponServicio.obtenerCupon(cuponId) );
    }

    @Test
    public void listarCuponesTest() {
        // Obtener la lista de todos los cupones
        List<ItemCuponDTO> listaCupones = cuponServicio.obtenerTodosLosCupones();

        listaCupones.forEach(lista -> System.out.println("Cupon: " + lista));

        // Verificar que la lista no sea nula y contenga al menos un cupón
        assertNotNull(listaCupones);
        assertTrue(!listaCupones.isEmpty());
    }
}
