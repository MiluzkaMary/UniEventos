package co.edu.uniquindio.unieventos.services.interfaces;

import co.edu.uniquindio.unieventos.model.documents.Cupon;

import java.util.List;

public interface CuponServicio {

    /**
     * Creates a new coupon.
     *
     * @param cupon the coupon to create
     * @return the created coupon
     */
    Cupon crearCupon(Cupon cupon);

    /**
     * Updates an existing coupon.
     *
     * @param cuponId the ID of the coupon to update
     * @param cupon the updated coupon details
     * @return the updated coupon
     */
    Cupon actualizarCupon(String cuponId, Cupon cupon);

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
    Cupon obtenerCupon(String cuponId);

    /**
     * Retrieves all coupons.
     *
     * @return the list of all coupons
     */
    List<Cupon> obtenerTodosLosCupones();
}