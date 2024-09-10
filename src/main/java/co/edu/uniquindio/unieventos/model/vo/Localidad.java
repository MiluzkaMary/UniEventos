package co.edu.uniquindio.unieventos.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class Localidad {

    private String nombre;
    private double precio;
    private int capacidadMaxima;
    private int capacidadDisponible;
}
