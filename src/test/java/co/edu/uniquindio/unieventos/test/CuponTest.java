package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.model.documents.Cupon;
import co.edu.uniquindio.unieventos.model.enums.EstadoCupon;
import co.edu.uniquindio.unieventos.model.enums.TipoCupon;
import co.edu.uniquindio.unieventos.repositories.CuponRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CuponTest {

    @Autowired
    private CuponRepo cuponRepo;

    @Test
    public void registrarTest() {
        Cupon cupon = Cupon.builder()
                .codigo("DESCUENTO10")
                .nombre("Descuento del 10%")
                .descuento(0.10)
                .fechaVencimiento(LocalDateTime.now().plusMonths(1))
                .estado(EstadoCupon.DISPONIBLE)
                .tipo(TipoCupon.UNICO)
                .build();

        // Guardar el cupon
        Cupon cuponCreado = cuponRepo.save(cupon);

        // Verificar que el cupon no sea null
        assertNotNull(cuponCreado);
    }

    @Test
    public void actualizarTest() {
        // Obtener cupon por id
        Cupon cupon = cuponRepo.findById("XXX").orElseThrow();
        // Modificar el nombre del cupon
        cupon.setNombre("Descuento del 20%");
        cupon.setDescuento(0.20);

        // Guardar
        cuponRepo.save(cupon);

        // Obtener el cupon actualizado
        Cupon cuponActualizado = cuponRepo.findById("XXX").orElseThrow();

        // Verificar actualización
        assertEquals("Descuento del 20%", cuponActualizado.getNombre());
    }

    @Test
    public void listarTodosTest() {
        List<Cupon> lista = cuponRepo.findAll();

        lista.forEach(System.out::println);

        // Verificar que la lista no esté vacía
        assertFalse(lista.isEmpty());
    }

    @Test
    public void eliminarTest() {
        // Borrar cupon con id
        cuponRepo.deleteById("XXX");

        // Obtener el cupon eliminado
        Cupon cupon = cuponRepo.findById("XXX").orElse(null);

        // Verificar que es null
        assertNull(cupon);
    }
}
