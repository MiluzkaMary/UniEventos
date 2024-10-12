package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.model.documents.Orden;
import co.edu.uniquindio.unieventos.model.vo.DetalleOrden;
import co.edu.uniquindio.unieventos.model.vo.Pago;
import co.edu.uniquindio.unieventos.model.enums.Pasarela;
import co.edu.uniquindio.unieventos.repositories.OrdenRepo;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrdenTest {

    @Autowired
    private OrdenRepo ordenRepo;

    @Test
    public void registrarTest() {
        Orden orden = Orden.builder()
                .idCuenta(new ObjectId())
                .idCupon(new ObjectId())
                .pago(Pago.builder()
                        .fecha(LocalDateTime.now())
                        .totalPago(150.0)
                        .estado("Pagado")
                        .metodoPago(Pasarela.PayPal)
                        .build())
                .fecha(LocalDateTime.now())
                .codigoQR("QR123456")
                .items(Collections.singletonList(
                        DetalleOrden.builder()
                                .id("001")
                                .idEvento(new ObjectId("66e2047ffe1c6311babc5b8c"))
                                .cantidad(2)
                                .nombreLocalidad("Localidad A")
                                .precio(80.0)
                                .precioUnitario(40.0)
                                .build()
                ))
                .total(150.0)
                .build();

        // Guardar la orden
        Orden ordenCreada = ordenRepo.save(orden);

        // Verificar que la orden no sea null
        assertNotNull(ordenCreada);
    }

    @Test
    public void actualizarTest() {
        // Obtener la orden por id
        Orden orden = ordenRepo.findById("67123ac1049eabf334b62c3b").orElseThrow();
        // Modificar el total de la orden
        orden.setTotal(200.0);

        // Guardar
        ordenRepo.save(orden);

        // Obtener la orden actualizada
        Orden ordenActualizada = ordenRepo.findById("67123ac1049eabf334b62c3b").orElseThrow();

        // Verificar actualización
        assertEquals(200.0, ordenActualizada.getTotal());
    }

    @Test
    public void listarTodosTest() {
        List<Orden> lista = ordenRepo.findAll();

        lista.forEach(System.out::println);

        // Verificar que la lista no esté vacía
        assertFalse(lista.isEmpty());
    }

    @Test
    public void eliminarTest() {
        // Borrar orden con id
        ordenRepo.deleteById("67123ac1049eabf334b62c3b");

        // Obtener la orden eliminada
        Orden orden = ordenRepo.findById("67123ac1049eabf334b62c3b").orElse(null);

        // Verificar que es null
        assertNull(orden);
    }
}
