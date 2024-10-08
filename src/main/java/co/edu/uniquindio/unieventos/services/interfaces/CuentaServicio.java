package co.edu.uniquindio.unieventos.services.interfaces;

import co.edu.uniquindio.unieventos.dto.CrearCuentaDTO;
import co.edu.uniquindio.unieventos.dto.EditarCuentaDTO;
import co.edu.uniquindio.unieventos.dto.InformacionCuentaDTO;
import co.edu.uniquindio.unieventos.dto.ItemCuentaDTO;
import co.edu.uniquindio.unieventos.model.documents.Cuenta;

import java.util.List;

public interface CuentaServicio {

    String crearCuenta(CrearCuentaDTO cuenta) throws Exception;

    void editarCuenta( EditarCuentaDTO cuenta) throws Exception;

    void eliminarCuenta(String id) throws Exception;

    InformacionCuentaDTO obtenerInformacionCuenta(String id) throws Exception;

    List<ItemCuentaDTO> listarCuentas();


}
