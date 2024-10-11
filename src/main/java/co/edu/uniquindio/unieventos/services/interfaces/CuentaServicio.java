package co.edu.uniquindio.unieventos.services.interfaces;

import co.edu.uniquindio.unieventos.dto.cuenta.*;
import co.edu.uniquindio.unieventos.dto.otros.TokenDTO;


import java.util.List;

public interface CuentaServicio {

    String crearCuenta(CrearCuentaDTO cuenta) throws Exception;

    void editarCuenta(EditarCuentaDTO cuenta) throws Exception;

    void eliminarCuenta(String id) throws Exception;



    InformacionCuentaDTO obtenerInformacionCuenta(String id) throws Exception;

    List<ItemCuentaDTO> listarCuentas();

    TokenDTO iniciarSesion(LoginDTO loginDTO) throws Exception;


}
