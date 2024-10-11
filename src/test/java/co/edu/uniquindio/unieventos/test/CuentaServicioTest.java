package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.dto.cuenta.CrearCuentaDTO;
import co.edu.uniquindio.unieventos.dto.cuenta.UsuarioDTO;
import co.edu.uniquindio.unieventos.model.enums.Rol;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniquindio.unieventos.services.interfaces.CuentaServicio;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CuentaServicioTest {

    @Autowired
    private  CuentaServicio cuentaServicio;

    @Test
    public void crearCuentaTest(){

        UsuarioDTO crearUsuarioDTO = new UsuarioDTO(
                "123",
                "Pepito perez",
                "123456",
                "avenida 1");
        // Crear un DTO con los datos para crear una nueva cuenta
        CrearCuentaDTO crearCuentaDTO = new CrearCuentaDTO(
                "pepitoperez@email.com",
                "password",
                Rol.CLIENTE,
                LocalDateTime.now(),
                crearUsuarioDTO
        );





        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = cuentaServicio.crearCuenta(crearCuentaDTO);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );


    }

}
