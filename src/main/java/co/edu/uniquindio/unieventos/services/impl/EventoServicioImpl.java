package co.edu.uniquindio.unieventos.services.impl;


import co.edu.uniquindio.unieventos.dto.evento.CrearEventoDTO;
import co.edu.uniquindio.unieventos.dto.evento.EditarEventoDTO;
import co.edu.uniquindio.unieventos.dto.evento.InformacionEventoDTO;
import co.edu.uniquindio.unieventos.dto.evento.ItemEventoDTO;
import co.edu.uniquindio.unieventos.model.documents.Evento;
import co.edu.uniquindio.unieventos.model.enums.EstadoCuenta;
import co.edu.uniquindio.unieventos.model.enums.EstadoEvento;
import co.edu.uniquindio.unieventos.model.vo.DetalleCarrito;
import co.edu.uniquindio.unieventos.model.vo.Localidad;
import co.edu.uniquindio.unieventos.repositories.EventoRepo;
import co.edu.uniquindio.unieventos.services.interfaces.EventoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EventoServicioImpl implements EventoServicio {

    private final EventoRepo eventoRepo;

    @Override
    public String crearEvento(CrearEventoDTO eventoDTO) throws Exception {

        // Verificar si ya existe un cupón con el mismo nombre
        if (existeNombre(eventoDTO.nombre())) {
            throw new Exception("El nombre del evento ya está en uso: " + eventoDTO.nombre());
        }

        // Mapeamos los datos del DTO a un objeto de tipo Evento
        Evento nuevoEvento = Evento.builder()
                .nombre(eventoDTO.nombre())
                .descripcion(eventoDTO.descripcion())
                .ciudad(eventoDTO.ciudad())
                .direccion(eventoDTO.direccion())
                .fecha(eventoDTO.fecha())
                .localidades(eventoDTO.localidades().stream()
                        .map(localidad -> new Localidad(
                                localidad.nombre(),
                                localidad.precio(),
                                localidad.capacidadMaxima(),
                                localidad.capacidadDisponible()))
                        .toList())
                .imagenPortada(eventoDTO.imagenPortada())
                .imagenLocalidades(eventoDTO.imagenLocalidades())
                .tipo(eventoDTO.tipo())
                .estado(EstadoEvento.ACTIVO) // Estado por defecto al crear un evento
                .build();

        // Guardamos el evento en la base de datos
        Evento eventoCreado = eventoRepo.save(nuevoEvento);
        return eventoCreado.getId();  // Retornar el ID del evento creado
    }

    @Override
    public void actualizarEvento(EditarEventoDTO eventoDTO) throws Exception {
        // Buscar el evento existente por ID
        Evento eventoExistente = eventoRepo.findById(eventoDTO.id())
                .orElseThrow(() -> new Exception("Evento no encontrado"));

        // Actualizar los datos del evento existente con los datos del DTO
        eventoExistente.setNombre(eventoDTO.nombre());
        eventoExistente.setDescripcion(eventoDTO.descripcion());
        eventoExistente.setCiudad(eventoDTO.ciudad());
        eventoExistente.setDireccion(eventoDTO.direccion());
        eventoExistente.setFecha(eventoDTO.fecha());
        eventoExistente.setLocalidades(eventoDTO.localidades().stream()
                .map(localidad -> new Localidad(
                        localidad.nombre(),
                        localidad.precio(),
                        localidad.capacidadMaxima(),
                        localidad.capacidadDisponible()))
                .toList());
        eventoExistente.setImagenPortada(eventoDTO.imagenPortada());
        eventoExistente.setImagenLocalidades(eventoDTO.imagenLocalidades());
        eventoExistente.setTipo(eventoDTO.tipo());
        eventoExistente.setEstado(eventoDTO.estado()); // Puede actualizar el estado si es necesario

        // Guardar el evento actualizado en la base de datos
        eventoRepo.save(eventoExistente);
    }

    @Override
    public void eliminarEvento(String Id) throws Exception {
        // Buscar el evento existente por ID
        Evento eventoExistente = eventoRepo.findById(Id)
                .orElseThrow(() -> new Exception("Evento no encontrado"));

        // Cambiar el estado del evento a NO_DISPONIBLE (o cualquier lógica de eliminación)
        eventoExistente.setEstado(EstadoEvento.INACTIVO);

        // Guardar el cambio en la base de datos
        eventoRepo.save(eventoExistente);
    }

    @Override
    public InformacionEventoDTO obtenerInformacionEvento(String Id) throws Exception {
        // Buscar el evento existente por ID
        Evento eventoExistente = eventoRepo.findById(Id)
                .orElseThrow(() -> new Exception("Evento no encontrado"));

        // Verificar si la cuenta tiene el estado ELIMINADO
        if (eventoExistente.getEstado() == EstadoEvento.INACTIVO) {
            throw new Exception("Evento Eliminado");
        }

        // Mapear los datos del evento a un DTO de información de evento
        return new InformacionEventoDTO(
                eventoExistente.getId(),
                eventoExistente.getNombre(),
                eventoExistente.getDescripcion(),
                eventoExistente.getCiudad(),
                eventoExistente.getDireccion(),
                eventoExistente.getFecha(),
                eventoExistente.getLocalidades(),
                eventoExistente.getImagenPortada(),
                eventoExistente.getImagenLocalidades(),
                eventoExistente.getTipo(),
                eventoExistente.getEstado());
    }

    private Evento convertirDtoAEvento(InformacionEventoDTO eventoDTO) {
        return Evento.builder()
                .nombre(eventoDTO.nombre())
                .descripcion(eventoDTO.descripcion())
                .ciudad(eventoDTO.ciudad())
                .direccion(eventoDTO.direccion())
                .fecha(eventoDTO.fecha())
                .localidades(eventoDTO.localidades())
                .tipo(eventoDTO.tipo())
                .estado(eventoDTO.estado())
                .build();
    }


    @Override
    public List<ItemEventoDTO> obtenerTodosLosEventos() throws Exception {
        // Obtener todos los eventos de la base de datos
        List<Evento> eventos = eventoRepo.findAll();

        // Mapear los eventos a una lista de ItemEventoDTO
        return eventos.stream()
                .map(evento -> new ItemEventoDTO(
                        evento.getNombre(),
                        evento.getDescripcion(),
                        evento.getFecha(),
                        evento.getDireccion()))
                .toList();
    }

    private boolean existeNombre(String nombre) {
        return eventoRepo.findByNombre(nombre).isPresent();
    }
}
