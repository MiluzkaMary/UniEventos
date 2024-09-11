package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.model.documents.Cuenta;
import co.edu.uniquindio.unieventos.model.vo.Usuario;
import co.edu.uniquindio.unieventos.model.enums.EstadoCuenta;
import co.edu.uniquindio.unieventos.model.enums.Rol;
import co.edu.uniquindio.unieventos.repositories.CuentaRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CuentaTest {

    @Autowired
    private CuentaRepo cuentaRepo;

    @Test
    public void registrarTest(){
        Cuenta cuenta = Cuenta.builder()
                .correo("carlos@email.com")
                .password("44444")
                .fechaRegistro(LocalDateTime.now())
                .estado(EstadoCuenta.ACTIVO)
                .usuario(
                        Usuario.builder()
                                .cedula("321")
                                .nombre("Carlos")
                                .direccion("Calle 321")
                                .telefono("4343434").build()
                )
                .rol(Rol.CLIENTE).build();


        //Guardar
        Cuenta cuentaCreada = cuentaRepo.save(cuenta);


        //Verificar q no sea null
        assertNotNull(cuentaCreada);
    }

    @Test
    public void actualizarTest(){
        //Obtener cuenta con id
        Cuenta cuenta = cuentaRepo.findById("66e1fd35a4237a361b70250e").orElseThrow();
        //Modificar el email de la cuenta
        cuenta.setCorreo("mary@email.com");
        cuenta.setUsuario(Usuario.builder()
                .cedula("555")
                .nombre("Mary")
                .direccion("Calle 777")
                .telefono("5565656").build());

        //Guardar
        cuentaRepo.save(cuenta);

        //Obtenemos la cuenta con el id
        Cuenta cuentaActualizada = cuentaRepo.findById("66e1fd35a4237a361b70250e").orElseThrow();;

        //Verificar actualizacion
        assertEquals("mary@email.com", cuentaActualizada.getCorreo());
    }

    @Test
    public void listarTodosTest(){
        List<Cuenta> lista = cuentaRepo.findAll();

        lista.forEach(System.out::println);


        assertEquals(2, lista.size());
    }

    @Test
    public void eliminarTest(){
        //Borramos cuenta con id
        cuentaRepo.deleteById("66e200f9b91da75f1a6219ab");


        //Obtenemos la cuenta eliminada
        Cuenta cuenta = cuentaRepo.findById("66e200f9b91da75f1a6219ab").orElse(null);


        //deberia devolver null
        assertNull(cuenta);
    }


}
