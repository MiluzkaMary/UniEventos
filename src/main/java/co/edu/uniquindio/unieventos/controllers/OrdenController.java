package co.edu.uniquindio.unieventos.controllers;

import co.edu.uniquindio.unieventos.dto.orden.CrearOrdenDTO;
import co.edu.uniquindio.unieventos.dto.orden.EditarOrdenDTO;
import co.edu.uniquindio.unieventos.dto.orden.InformacionOrdenDTO;
import co.edu.uniquindio.unieventos.dto.orden.ItemOrdenDTO;
import co.edu.uniquindio.unieventos.dto.otros.MensajeDTO;
import co.edu.uniquindio.unieventos.services.interfaces.OrdenServicio;
import com.mercadopago.resources.preference.Preference;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orden")
@RequiredArgsConstructor
public class OrdenController {

    private final OrdenServicio ordenServicio;

    /**
     * Creates a new order.
     *
     * @param orden the order to create
     * @return a response entity with a message about the order creation status
     */
    @PostMapping("/crear-orden")
    public ResponseEntity<MensajeDTO<String>> crearOrden(@Valid @RequestBody CrearOrdenDTO orden) throws Exception {
        ordenServicio.crearOrden(orden);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Orden creada exitosamente"));
    }

    /**
     * Updates an existing order.
     *
     * @param orden the updated order details
     * @return a response entity with a message about the order update status
     */
    @PutMapping("/editar-orden")
    public ResponseEntity<MensajeDTO<String>> editarOrden(@Valid @RequestBody EditarOrdenDTO orden) throws Exception {
        ordenServicio.editarOrden(orden);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Orden editada exitosamente"));
    }

    /**
     * Deletes an order by its ID.
     *
     * @param id the ID of the order to delete
     * @return a response entity with a message about the order deletion status
     */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarOrden(@PathVariable String id) throws Exception {
        ordenServicio.eliminarOrden(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Orden eliminada exitosamente"));
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param id the ID of the order
     * @return a response entity containing the order information
     */
    @GetMapping("/obtener/{id}")
    public ResponseEntity<MensajeDTO<InformacionOrdenDTO>> obtenerInformacionOrden(@PathVariable String id) throws Exception {
        InformacionOrdenDTO infoOrden = ordenServicio.obtenerInformacionOrden(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, infoOrden));
    }

    /**
     * Retrieves all orders.
     *
     * @return a response entity containing the list of all orders
     */
    @GetMapping("/listar-todo")
    public ResponseEntity<MensajeDTO<List<ItemOrdenDTO>>> listarOrdenes() {
        List<ItemOrdenDTO> lista = ordenServicio.listarOrdenes();
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

    /**
     * Processes a payment using MercadoPago.
     *
     * @param idOrden the ID of the order to process payment for
     * @return a response entity containing the payment preference information
     */
    @PostMapping("/realizar-pago/{idOrden}")
    public ResponseEntity<MensajeDTO<Preference>> realizarPago(@RequestParam("idOrden") String idOrden) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, ordenServicio.realizarPago(idOrden)));
    }


    /**
     * Receives MercadoPago notifications.
     *
     * @param requestBody the notification data from MercadoPago
     */
    @PostMapping("/notificacion-pago")
    public void recibirNotificacionMercadoPago(@RequestBody Map<String, Object> requestBody) {
        ordenServicio.recibirNotificacionMercadoPago(requestBody);
    }


}
