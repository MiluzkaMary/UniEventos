package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.dto.evento.*;
import co.edu.uniquindio.unieventos.model.enums.EstadoEvento;
import co.edu.uniquindio.unieventos.model.enums.TipoEvento;
import co.edu.uniquindio.unieventos.model.vo.Localidad;
import co.edu.uniquindio.unieventos.services.interfaces.EventoServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EventoServicioTest {

    @Autowired
    private EventoServicio eventoServicio;

    @Test
    public void crearEventoTest() {
        // Crear una lista de localidades
        List<LocalidadDTO> localidades = List.of(
                new LocalidadDTO("VIP", 100.0, 50, 50),
                new LocalidadDTO("General", 50.0, 200, 200)
        );

        // Crear el DTO con los datos para un nuevo evento
        CrearEventoDTO crearEventoDTO = new CrearEventoDTO(
                "Concierto de Jazz",
                "Concierto de jazz con varias bandas",
                "Bogota",
                "Avenida 123",
                LocalDateTime.now().plusDays(10),  // Fecha en 10 días
                localidades,
                "imagenPortada.png",
                "imagenLocalidades.png",
                TipoEvento.CONCIERTO,
                EstadoEvento.INACTIVO
        );

        // Verificar que no se lanza ninguna excepción y se crea el evento
        assertDoesNotThrow(() -> {
            String id = eventoServicio.crearEvento(crearEventoDTO);
            assertNotNull(id);  // Verificar que el ID del evento no es nulo
        });
    }

    @Test
    public void editarEventoTest() throws Exception {
        // Asignar un ID de un evento existente, por ejemplo, del dataset
        String idEvento = "6708e32c6a83f73e3f08ea89";

        // Crear nuevas localidades para la edición
        List<LocalidadDTO> localidades = List.of(
                new LocalidadDTO("Platea 1", 120.0, 60, 60),
                new LocalidadDTO("General", 55.0, 220, 220)
        );

        // Crear el DTO con los nuevos datos para editar el evento
        EditarEventoDTO editarEventoDTO = new EditarEventoDTO(
                idEvento,
                "Concierto de Rap",
                "Tus mejores artistas de rap",
                "NY",
                "Avenida 456",
                LocalDateTime.now().plusDays(15),
                localidades,
                "nuevaImagenPortada.png",
                "nuevaImagenLocalidades.png",
                TipoEvento.COMEDIA,
                EstadoEvento.ACTIVO  // Actualizar el estado
        );

        // Verificar que no se lanza ninguna excepción y el evento se actualiza correctamente
        assertDoesNotThrow(() -> {
            eventoServicio.actualizarEvento(editarEventoDTO);

            // Obtener la información del evento actualizado
            InformacionEventoDTO eventoActualizado = eventoServicio.obtenerInformacionEvento(idEvento);

            // Verificar que los datos se actualizaron correctamente
            assertEquals("Concierto de Rap", eventoActualizado.nombre());
            assertEquals("NY", eventoActualizado.ciudad());
            assertEquals(EstadoEvento.ACTIVO, eventoActualizado.estado());
            assertEquals("Avenida 456", eventoActualizado.direccion());
        });
    }

    @Test
    public void eliminarEventoTest() throws Exception {
        // Asignar un ID de un evento existente, por ejemplo, del dataset
        String idEvento = "67089506a687a5005f789969";

        // Eliminar el evento
        assertDoesNotThrow(() -> eventoServicio.eliminarEvento(idEvento));

        // Intentar obtener el evento debe lanzar una excepción
        assertThrows(Exception.class, () -> eventoServicio.obtenerInformacionEvento(idEvento));
    }

    @Test
    public void listarEventosTest() {
        // Obtener todos los eventos
        List<ItemEventoDTO> eventos = assertDoesNotThrow(() -> eventoServicio.obtenerTodosLosEventos());

        eventos.forEach(evento -> System.out.println("Evento: " + evento));

        // Verificar que la lista no sea nula y tenga los elementos esperados
        assertNotNull(eventos);
        assertTrue(eventos.size() > 0);
    }
}
