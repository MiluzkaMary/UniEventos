package co.edu.uniquindio.unieventos.services.interfaces;

import co.edu.uniquindio.unieventos.model.documents.Carrito;
import co.edu.uniquindio.unieventos.model.vo.DetalleCarrito;

import java.util.List;

public interface CarritoServicio {

    /**
     * Adds an item to the shopping cart.
     *
     * @param carritoId the ID of the shopping cart
     * @param detalleCarrito the details of the item to add
     * @return the updated shopping cart
     */
    Carrito agregarItem(String carritoId, DetalleCarrito detalleCarrito);

    /**
     * Removes an item from the shopping cart.
     *
     * @param carritoId the ID of the shopping cart
     * @param itemId the ID of the item to remove
     * @return the updated shopping cart
     */
    Carrito eliminarItem(String carritoId, String itemId);

    /**
     * Retrieves the shopping cart by its ID.
     *
     * @param carritoId the ID of the shopping cart
     * @return the shopping cart
     */
    Carrito obtenerCarrito(String carritoId);

    /**
     * Clears all items from the shopping cart.
     *
     * @param carritoId the ID of the shopping cart
     * @return the updated shopping cart
     */
    Carrito vaciarCarrito(String carritoId);

    /**
     * Retrieves all items in the shopping cart.
     *
     * @param carritoId the ID of the shopping cart
     * @return the list of items in the shopping cart
     */
    List<DetalleCarrito> obtenerItems(String carritoId);
}