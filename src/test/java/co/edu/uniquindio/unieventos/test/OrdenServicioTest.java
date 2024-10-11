package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.dto.orden.*;
import co.edu.uniquindio.unieventos.model.enums.Pasarela;
import co.edu.uniquindio.unieventos.model.vo.Pago;
import co.edu.uniquindio.unieventos.services.interfaces.OrdenServicio;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrdenServicioTest {

    @Autowired
    private OrdenServicio ordenServicio;

    @Test
    public void crearOrdenTest() {
        // Crear detalles de la orden
        DetalleOrdenDTO detalle1 = new DetalleOrdenDTO(
                "001", new ObjectId("670888be027cdf5cd2c6200d"), 2, "General", 100.0, 50.0
        ); //Teatro Contemporaneo

        List<DetalleOrdenDTO> items = List.of(detalle1);

        // Crear objeto Pago
        PagoDTO pago = new PagoDTO(
                "PAY-001",                         // Código de pago
                LocalDateTime.now(),              // Fecha del pago
                100.0,                            // Total
                "APROBADO",                       // Estado del pago
                "Estado detalle de la transacción", // Detalle del estado
                Pasarela.MercadoPago,             // Método de pago
                "COP",                            // Moneda
                "AUTH-12345"                      // Código de autorización
        );
        // Crear DTO para nueva orden
        CrearOrdenDTO crearOrdenDTO = new CrearOrdenDTO(
                new ObjectId("670884ae3dd28a6ea935f6c0"), // Carlos Gomez
                new ObjectId("671234e76a8f1eab9d71b024"), // Cupon EXTRA 5
                pago,
                LocalDateTime.now(),
                "QR123",
                items,
                100.0,
                "MercadoPago"
        );

        // Verificar que la orden se cree sin lanzar excepción
        assertDoesNotThrow(() -> {
            String idOrden = ordenServicio.crearOrden(crearOrdenDTO);
            assertNotNull(idOrden); // Verificar que el ID de la orden no sea nulo
        });
    }

    @Test
    public void editarOrdenTest() throws Exception {
        // ID de la orden a editar (debe existir en el dataset)
        String idOrden = "6708fb16a8bce0725fb6d3e1";

        // Crear detalles de la orden
        DetalleOrdenDTO detalle1 = new DetalleOrdenDTO(
                "prod1", new ObjectId("670888b687c495460cab2526"), 3, "VIP",  450.0,150.0
        ); //FERIA DEL LIBRO

        List<DetalleOrdenDTO> items = List.of(detalle1);

        // Crear objeto Pago actualizado
        PagoDTO pago = new PagoDTO(
                "PAY-001",                         // Código de pago
                LocalDateTime.now(),              // Fecha del pago
                100.0,                            // Total
                "APROBADO",                       // Estado del pago
                "Estado detalle de la transacción", // Detalle del estado
                Pasarela.MercadoPago,             // Método de pago
                "COP",                            // Moneda
                "AUTH-12345"                      // Código de autorización
        );
        // Crear DTO para editar la orden
        EditarOrdenDTO editarOrdenDTO = new EditarOrdenDTO(
                idOrden,
                new ObjectId("670884ae3dd28a6ea935f6c0"), // ID de la cuenta
                new ObjectId("671234b8c9d849f64713e920"), // VIP ONLY UNICO
                pago,
                LocalDateTime.now(),
                "QR124", // Nuevo código QR
                items,
                450.0,
                "MercadoPago"
        );

        // Verificar que la orden se edite sin lanzar excepción
        assertDoesNotThrow(() -> {
            ordenServicio.editarOrden(editarOrdenDTO);

            // Verificar que la orden fue editada correctamente
            InformacionOrdenDTO infoOrden = ordenServicio.obtenerInformacionOrden(idOrden);
            assertEquals("QR124", infoOrden.codigoQR());
            assertEquals(450.0, infoOrden.total());
        });
    }

    @Test
    public void eliminarOrdenTest() throws Exception {
        // ID de la orden a eliminar (debe existir en el dataset)
        String idOrden = "6708fb16a8bce0725fb6d3e1";

        // Eliminar la orden
        assertDoesNotThrow(() -> {
            ordenServicio.eliminarOrden(idOrden);
        });

        // Intentar obtener la orden eliminada debe lanzar una excepción
        assertThrows(Exception.class, () -> {
            ordenServicio.obtenerInformacionOrden(idOrden);
        });
    }

    @Test
    public void obtenerInformacionOrdenTest() throws Exception {
        // ID de la orden a obtener (debe existir en el dataset)
        String idOrden = "6708fb16a8bce0725fb6d3e1";

        // Verificar que la información de la orden se obtenga sin excepción
        InformacionOrdenDTO infoOrden = ordenServicio.obtenerInformacionOrden(idOrden);
        assertNotNull(infoOrden);
        assertEquals("QR124", infoOrden.codigoQR());
    }

    @Test
    public void listarOrdenesTest() {
        // Verificar que las órdenes se puedan listar sin excepción
        List<ItemOrdenDTO> listaOrdenes = ordenServicio.listarOrdenes();

        listaOrdenes.forEach(orden -> System.out.println("Orden: " + orden));

        // Verificar que la lista no sea nula y contenga registros
        assertNotNull(listaOrdenes);
        assertTrue(!listaOrdenes.isEmpty()); // Cambiar el número según la cantidad de registros en el dataset
    }
}
