package co.edu.uniquindio.unieventos.services.interfaces;

import co.edu.uniquindio.unieventos.dto.orden.CrearOrdenDTO;
import co.edu.uniquindio.unieventos.dto.orden.EditarOrdenDTO;
import co.edu.uniquindio.unieventos.dto.orden.InformacionOrdenDTO;
import co.edu.uniquindio.unieventos.dto.orden.ItemOrdenDTO;
import com.mercadopago.resources.preference.Preference;
import java.util.List;
import java.util.Map;

public interface OrdenServicio {

    /**
     * Creates a new order.
     *
     * @param orden DTO for creating the order
     * @return the created order's ID
     * @throws Exception if an error occurs during order creation
     */
    String crearOrden(CrearOrdenDTO orden) throws Exception;

    /**
     * Edits an existing order.
     *
     * @param orden DTO for editing the order
     * @throws Exception if an error occurs during order update
     */
    void editarOrden(EditarOrdenDTO orden) throws Exception;

    /**
     * Deletes an order by its ID.
     *
     * @param id the ID of the order to delete
     * @throws Exception if an error occurs during order deletion
     */
    void eliminarOrden(String id) throws Exception;

    /**
     * Retrieves an order by its ID.
     *
     * @param id the ID of the order to retrieve
     * @return DTO containing order information
     * @throws Exception if an error occurs during order retrieval
     */
    InformacionOrdenDTO obtenerInformacionOrden(String id) throws Exception;

    /**
     * Retrieves all orders.
     *
     * @return a list of DTOs representing all orders
     */
    List<ItemOrdenDTO> listarOrdenes();


    /**
     * Pasarela MercadoPago
     */
    Preference realizarPago(String idOrden) throws Exception;

    void recibirNotificacionMercadoPago(Map<String, Object> request);

}
