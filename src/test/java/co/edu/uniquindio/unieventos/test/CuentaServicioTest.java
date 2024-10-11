package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.dto.cuenta.*;
import co.edu.uniquindio.unieventos.model.enums.EstadoCuenta;
import co.edu.uniquindio.unieventos.model.enums.Rol;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniquindio.unieventos.services.interfaces.CuentaServicio;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class CuentaServicioTest {

    @Autowired
    private CuentaServicio cuentaServicio;

    @Test
    public void crearCuentaTest() {

        UsuarioDTO crearUsuarioDTO = new UsuarioDTO(
                "12121",
                "Mary Saire",
                "1929109",
                "Av 19");
        // Crear un DTO con los datos para crear una nueva cuenta
        CrearCuentaDTO crearCuentaDTO = new CrearCuentaDTO(
                "mary512@gmail.com",
                "23828",
                Rol.CLIENTE,
                LocalDateTime.now().minusYears(23),
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
        String idCuenta = "67090e11974f3c6c277266e0";

        UsuarioDTO usuarioDTO= new UsuarioDTO(
                "Nueva cedula",
                "Nuevo nombre",
                "Nuevo tlfn",
                "Nueva direc"
        );
        //Se crea un objeto de tipo EditarCuentaDTO
        EditarCuentaDTO editarCuentaDTO = new EditarCuentaDTO(
                idCuenta,
                "prueba@email.com",
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
            assertEquals("Nueva direc", detalle.usuario().getDireccion());
            assertEquals("Nueva cedula", detalle.usuario().getCedula());
            assertEquals("Nuevo nombre", detalle.usuario().getNombre());
            assertEquals("Nuevo tlfn", detalle.usuario().getTelefono());
            assertEquals("pepitoperez2@email.com", detalle.correo());
            assertEquals(EstadoCuenta.INACTIVO, detalle.estado());

        });
    }

    @Test
    public void eliminarCuentaTest(){


        //Se define el id de la cuenta del usuario a eliminar, este id está en el dataset.js
        String idCuenta = "67090c759fd294640cd09d44";


        //Se elimina (cambia el estado) de la cuenta del usuario con el id definido
        assertDoesNotThrow(() -> cuentaServicio.eliminarCuenta(idCuenta) );


        //Al intentar obtener la cuenta del usuario con el id definido se debe lanzar una excepción
        assertThrows(Exception.class, () -> cuentaServicio.obtenerInformacionCuenta(idCuenta) );
    }


    @Test
    public void listarTest(){
        //Se obtiene la lista de las cuentas de los usuarios
        List<ItemCuentaDTO> lista = cuentaServicio.listarCuentas();

        // Se imprime la lista de cuentas para ver sus detalles
        lista.forEach(cuenta -> System.out.println("Cuenta: " + cuenta));

        //Se verifica que la lista no sea nula y que tenga 3 elementos (o los que hayan)
        assertEquals(8, lista.size());
    }




}
