package co.edu.uniquindio.unieventos.services.interfaces;

import co.edu.uniquindio.unieventos.model.documents.Evento;

import java.util.List;
public interface EventoServicio {

    /**
     * Creates a new event.
     *
     * @param evento the event to create
     * @return the created event
     */
    Evento crearEvento(Evento evento);

    /**
     * Updates an existing event.
     *
     * @param eventoId the ID of the event to update
     * @param evento the updated event details
     * @return the updated event
     */
    Evento actualizarEvento(String eventoId, Evento evento);

    /**
     * Deletes an event by its ID.
     *
     * @param eventoId the ID of the event to delete
     */
    void eliminarEvento(String eventoId);

    /**
     * Retrieves an event by its ID.
     *
     * @param eventoId the ID of the event to retrieve
     * @return the retrieved event
     */
    Evento obtenerEvento(String eventoId);

    /**
     * Retrieves all events.
     *
     * @return the list of all events
     */
    List<Evento> obtenerTodosLosEventos();
}