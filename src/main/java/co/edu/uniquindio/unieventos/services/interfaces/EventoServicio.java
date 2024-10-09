package co.edu.uniquindio.unieventos.services.interfaces;

import co.edu.uniquindio.unieventos.dto.evento.CrearEventoDTO;
import co.edu.uniquindio.unieventos.dto.evento.EditarEventoDTO;
import co.edu.uniquindio.unieventos.dto.evento.InformacionEventoDTO;
import co.edu.uniquindio.unieventos.dto.evento.ItemEventoDTO;

import java.util.List;

public interface EventoServicio {

    /**
     * Creates a new event.
     *
     * @param evento the event to create
     * @return the created event
     * @throws Exception if an error occurs during event creation
     */
    String crearEvento(CrearEventoDTO evento) throws Exception;

    /**
     * Updates an existing event.
     * 
     * @param evento the updated event details
     * @throws Exception if an error occurs during event update
     */
    void actualizarEvento(EditarEventoDTO evento) throws Exception;

    /**
     * Deletes an event by its ID.
     *
     * @param Id the ID of the event to delete
     * @throws Exception if an error occurs during event deletion
     */
    void eliminarEvento(String Id) throws Exception;

    /**
     * Retrieves an event by its ID.
     *
     * @param Id the ID of the event to retrieve
     * @return the event information
     * @throws Exception if an error occurs during event retrieval
     */
    InformacionEventoDTO obtenerInformacionEvento(String Id) throws Exception;

    /**
     * Retrieves all events.
     *
     * @return the list of all events
     * @throws Exception if an error occurs during event retrieval
     */
    List<ItemEventoDTO> obtenerTodosLosEventos() throws Exception;
}