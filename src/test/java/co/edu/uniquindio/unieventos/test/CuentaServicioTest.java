package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.dto.cuenta.CrearCuentaDTO;
import co.edu.uniquindio.unieventos.dto.cuenta.EditarCuentaDTO;
import co.edu.uniquindio.unieventos.dto.cuenta.InformacionCuentaDTO;
import co.edu.uniquindio.unieventos.dto.cuenta.UsuarioDTO;
import co.edu.uniquindio.unieventos.model.enums.EstadoCuenta;
import co.edu.uniquindio.unieventos.model.enums.Rol;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniquindio.unieventos.services.interfaces.CuentaServicio;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class CuentaServicioTest {

    @Autowired
    private CuentaServicio cuentaServicio;

    @Test
    public void crearCuentaTest() {

        UsuarioDTO crearUsuarioDTO = new UsuarioDTO(
                "123",
                "Pepito perez",
                "123456",
                "avenida 1");
        // Crear un DTO con los datos para crear una nueva cuenta
        CrearCuentaDTO crearCuentaDTO = new CrearCuentaDTO(
                "pepitoperez2@email.com",
                "password",
                Rol.CLIENTE,
                LocalDateTime.now(),
                crearUsuarioDTO);

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow(() -> {
            // Se crea la cuenta y se imprime el id
            String id = cuentaServicio.crearCuenta(crearCuentaDTO);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        });

    }

    @Test
    public void editarCuentaTest(){


        //Se define el id de la cuenta del usuario a actualizar, este id está en el dataset.js
        String idCuenta = "6708905d44c3e0456083e6e1";

        UsuarioDTO usuarioDTO= new UsuarioDTO(
                "nueva ced",
                "nuv nom",
                "nuv tel",
                "nuv dir"
        );
        //Se crea un objeto de tipo EditarCuentaDTO
        EditarCuentaDTO editarCuentaDTO = new EditarCuentaDTO(
                idCuenta,
                "pepitoperez2@email.com",
                "nueva password",
                usuarioDTO,
                EstadoCuenta.INACTIVO
        );


        //Se espera que no se lance ninguna excepción
        assertDoesNotThrow(() -> {
            //Se actualiza la cuenta del usuario con el id definido
            cuentaServicio.editarCuenta(editarCuentaDTO);


            //Obtenemos el detalle de la cuenta del usuario con el id definido
            InformacionCuentaDTO detalle = cuentaServicio.obtenerInformacionCuenta(idCuenta);


            //Se verifica que la dirección del usuario sea la actualizada
            assertEquals("nuv dir", detalle.usuario().getDireccion());
            assertEquals("nueva ced", detalle.usuario().getCedula());
            assertEquals("nuv nom", detalle.usuario().getNombre());
            assertEquals("nuv tel", detalle.usuario().getTelefono());
            assertEquals("pepitoperez2@email.com", detalle.correo());
            assertEquals(EstadoCuenta.INACTIVO, detalle.estado());

        });
    }

              

}
