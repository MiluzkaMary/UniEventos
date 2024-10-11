package co.edu.uniquindio.unieventos.dto.otros;




public record MensajeDTO<T>(
        boolean error,
        T respuesta
) {
}
