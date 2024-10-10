package co.edu.uniquindio.unieventos.repositories;

import co.edu.uniquindio.unieventos.model.documents.Cuenta;
import co.edu.uniquindio.unieventos.model.enums.EstadoCuenta;
import co.edu.uniquindio.unieventos.model.enums.Rol;
import co.edu.uniquindio.unieventos.model.vo.Usuario;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepo extends MongoRepository<Cuenta, String> {

    Optional<Cuenta> findByCorreo(String correo);

    Optional<Cuenta> findByUsuario(Usuario usuario);




}
