package co.edu.uniquindio.unieventos.controllers;

import co.edu.uniquindio.unieventos.dto.carrito.CrearCarritoDTO;
import co.edu.uniquindio.unieventos.dto.carrito.EditarCarritoDTO;
import co.edu.uniquindio.unieventos.dto.carrito.InformacionCarritoDTO;
import co.edu.uniquindio.unieventos.dto.carrito.ItemCarritoDTO;
import co.edu.uniquindio.unieventos.dto.otros.MensajeDTO;
import co.edu.uniquindio.unieventos.services.interfaces.CarritoServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoServicio carritoServicio;


    /**
     * Creates a new carrito.
     *
     * @param carrito the carrito to create
     * @return the created carrito's ID
     */
    @PostMapping("/crear-carrito")
    public ResponseEntity<MensajeDTO<String>> crearCarrito(@Valid @RequestBody CrearCarritoDTO carrito) throws Exception {
        carritoServicio.crearCarrito(carrito);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Carrito creado exitosamente"));
    }



    /**
     * Updates an existing carrito.
     *
     * @param carrito the updated carrito details
     */
    @PutMapping("/editar-carrito")
    public ResponseEntity<MensajeDTO<String>> editarCarrito(@Valid @RequestBody EditarCarritoDTO carrito) throws Exception {
        carritoServicio.editarCarrito(carrito);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Carrito editado exitosamente"));
    }


    /**
     * Deletes a carrito by its ID.
     *
     * @param id the ID of the carrito to delete
     */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarCarrito(@PathVariable String id) throws Exception {
        carritoServicio.eliminarCarrito(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Carrito eliminado exitosamente"));
    }


    /**
     * Retrieves a carrito by the account ID.
     *
     * @param idCuenta the ID of the account (Cuenta) associated with the carrito
     * @return the carrito information
     */
    @GetMapping("/obtener/{idCuenta}")
    public ResponseEntity<MensajeDTO<InformacionCarritoDTO>> obtenerCarritoPorCuenta(@PathVariable ObjectId idCuenta) throws Exception {
        InformacionCarritoDTO infoCarrito = carritoServicio.obtenerCarritoPorCuenta(idCuenta);

        return ResponseEntity.ok(new MensajeDTO<>(false, infoCarrito));
    }


    /**
     * Retrieves all carritos.
     *
     * @return the list of all carritos
     */
    @GetMapping("/listar-todo")
    public ResponseEntity<MensajeDTO<List<ItemCarritoDTO>>> listarCarritos() {
        List<ItemCarritoDTO> lista = carritoServicio.listarCarritos();
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }


}
