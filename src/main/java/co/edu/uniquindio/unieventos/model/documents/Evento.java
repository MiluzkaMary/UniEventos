package co.edu.uniquindio.unieventos.model.documents;

import co.edu.uniquindio.unieventos.model.enums.EstadoEvento;
import co.edu.uniquindio.unieventos.model.vo.Localidad;
import co.edu.uniquindio.unieventos.model.enums.TipoEvento;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("eventos")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Evento {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String nombre;
    private String descripcion;
    private String ciudad;
    private String direccion;
    private LocalDateTime fecha;
    private List<Localidad> localidades;
    private String imagenPortada;
    private String imagenLocalidades;
    private TipoEvento tipo;
    private EstadoEvento estado;

    @Builder
    public Evento(String id, String nombre, String descripcion, String ciudad, String direccion, LocalDateTime fecha, List<Localidad> localidades, String imagenPortada, String imagenLocalidades, TipoEvento tipo, EstadoEvento estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.fecha = fecha;
        this.localidades = localidades;
        this.imagenPortada = imagenPortada;
        this.imagenLocalidades = imagenLocalidades;
        this.tipo = tipo;
        this.estado = estado;
    }
}
