package co.edu.uniquindio.unieventos.dto.evento;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LocalidadDTO(
        @NotBlank String nombre,
        @Min(0) double precio, //positiva o cero
        @Min(1) int capacidadMaxima,
        @Min(0) int capacidadDisponible  //positiva o cero
) {}
