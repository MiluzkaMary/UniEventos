package co.edu.uniquindio.unieventos.services.interfaces;

import co.edu.uniquindio.unieventos.dto.otros.EmailDTO;
import co.edu.uniquindio.unieventos.model.documents.Cuenta;

public interface EmailServicio {

    void enviarCorreo(EmailDTO emailDTO) throws Exception;

    String generarCodigoCuenta();

}
