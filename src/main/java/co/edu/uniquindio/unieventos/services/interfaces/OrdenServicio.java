package co.edu.uniquindio.unieventos.services.interfaces;


import co.edu.uniquindio.unieventos.model.documents.Orden;

import java.util.List;

public interface OrdenServicio {

    /**
     * Creates a new order.
     *
     * @param orden the order to create
     * @return the created order
     */
    Orden crearOrden(Orden orden);

    /**
     * Updates an existing order.
     *
     * @param ordenId the ID of the order to update
     * @param orden the updated order details
     * @return the updated order
     */
    Orden actualizarOrden(String ordenId, Orden orden);

    /**
     * Deletes an order by its ID.
     *
     * @param ordenId the ID of the order to delete
     */
    void eliminarOrden(String ordenId);

    /**
     * Retrieves an order by its ID.
     *
     * @param ordenId the ID of the order to retrieve
     * @return the retrieved order
     */
    Orden obtenerOrden(String ordenId);

    /**
     * Retrieves all orders.
     *
     * @return the list of all orders
     */
    List<Orden> obtenerTodasLasOrdenes();
}