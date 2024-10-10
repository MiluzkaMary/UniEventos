package co.edu.uniquindio.unieventos.services.interfaces;

import co.edu.uniquindio.unieventos.dto.carrito.CrearCarritoDTO;
import co.edu.uniquindio.unieventos.dto.carrito.EditarCarritoDTO;
import co.edu.uniquindio.unieventos.dto.carrito.InformacionCarritoDTO;
import co.edu.uniquindio.unieventos.dto.carrito.ItemCarritoDTO;
import org.bson.types.ObjectId;

import java.util.List;

public interface CarritoServicio {

    /**
     * Creates a new carrito.
     *
     * @param carrito the carrito to create
     * @return the created carrito's ID
     */
    String crearCarrito(CrearCarritoDTO carrito) throws Exception;

    /**
     * Updates an existing carrito.
     *
     * @param carrito the updated carrito details
     */
    void editarCarrito(EditarCarritoDTO carrito) throws Exception;

    /**
     * Deletes a carrito by its ID.
     *
     * @param id the ID of the carrito to delete
     */
    void eliminarCarrito(String id) throws Exception;

    /**
     * Retrieves a carrito by the account ID.
     *
     * @param idCuenta the ID of the account (Cuenta) associated with the carrito
     * @return the carrito information
     */
    InformacionCarritoDTO obtenerCarritoPorCuenta(ObjectId idCuenta) throws Exception;

    /**
     * Retrieves all carritos.
     *
     * @return the list of all carritos
     */
    List<ItemCarritoDTO> listarCarritos();
}
