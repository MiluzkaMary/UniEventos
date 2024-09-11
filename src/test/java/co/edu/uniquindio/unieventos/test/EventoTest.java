package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.model.documents.Evento;
import co.edu.uniquindio.unieventos.model.enums.EstadoEvento;
import co.edu.uniquindio.unieventos.model.enums.TipoEvento;
import co.edu.uniquindio.unieventos.model.vo.Localidad;
import co.edu.uniquindio.unieventos.repositories.EventoRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EventoTest {

    @Autowired
    private EventoRepo eventoRepo;

    @Test
    public void registrarTest() {
        Evento evento = Evento.builder()
                .nombre("Sleeping with Sirens")
                .descripcion("Un concierto con bandas de rock internacional")
                .ciudad("Bogotá")
                .direccion("Estadio El Campín")
                .fecha(LocalDateTime.now().plusMonths(2))
                .localidades(List.of(
                        new Localidad("General", 10000, 500, 120),
                        new Localidad("VIP", 20000, 100, 23)
                ))
                .imagenPortada("portada.jpg")
                .imagenLocalidades("localidades.jpg")
                .tipo(TipoEvento.CONCIERTO)
                .estado(EstadoEvento.ACTIVO)
                .build();

        // Guardar el evento
        Evento eventoCreado = eventoRepo.save(evento);

        // Verificar que el evento no sea null
        assertNotNull(eventoCreado);
    }

    @Test
    public void actualizarTest() {
        // Obtener evento por id
        Evento evento = eventoRepo.findById("66e2047ffe1c6311babc5b8c").orElseThrow();
        // Modificar la ciudad del evento
        evento.setCiudad("Medellín");

        // Guardar
        eventoRepo.save(evento);

        // Obtener el evento actualizado
        Evento eventoActualizado = eventoRepo.findById("66e2047ffe1c6311babc5b8c").orElseThrow();

        // Verificar actualización
        assertEquals("Medellín", eventoActualizado.getCiudad());
    }

    @Test
    public void listarTodosTest() {
        List<Evento> lista = eventoRepo.findAll();

        lista.forEach(System.out::println);

        assertEquals(1, lista.size());
    }

    @Test
    public void eliminarTest() {
        // Borrar evento con id
        eventoRepo.deleteById("XXX");

        // Obtener el evento eliminado
        Evento evento = eventoRepo.findById("XXX").orElse(null);

        // Verificar que es null
        assertNull(evento);
    }
}
