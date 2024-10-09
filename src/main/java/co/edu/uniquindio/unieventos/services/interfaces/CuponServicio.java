package co.edu.uniquindio.unieventos.services.interfaces;

import co.edu.uniquindio.unieventos.dto.cupon.CrearCuponDTO;
import co.edu.uniquindio.unieventos.dto.cupon.EditarCuponDTO;
import co.edu.uniquindio.unieventos.dto.cupon.InformacionCuponDTO;
import co.edu.uniquindio.unieventos.dto.cupon.ItemCuponDTO;


import java.util.List;

public interface CuponServicio {

    /**
     * Creates a new coupon.
     *
     * @param cupon the coupon to create
     * @return the created coupon
     */
    String crearCupon(CrearCuponDTO cupon);

    /**
     * Updates an existing coupon.
     *
     *
     * @param cupon the updated coupon details
     * @return the updated coupon
     */
    void actualizarCupon(EditarCuponDTO cupon);

    /**
     * Deletes a coupon by its ID.
     *
     * @param cuponId the ID of the coupon to delete
     */
    void eliminarCupon(String cuponId);

    /**
     * Retrieves a coupon by its ID.
     *
     * @param cuponId the ID of the coupon to retrieve
     * @return the retrieved coupon
     */
    InformacionCuponDTO obtenerCupon(String cuponId);

    /**
     * Retrieves all coupons.
     *
     * @return the list of all coupons
     */
    List<ItemCuponDTO> obtenerTodosLosCupones();
}