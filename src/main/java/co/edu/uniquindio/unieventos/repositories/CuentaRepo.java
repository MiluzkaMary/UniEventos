package co.edu.uniquindio.unieventos.repositories;

import co.edu.uniquindio.unieventos.model.documents.Cuenta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepo extends MongoRepository<Cuenta, String> {
    Optional<Cuenta> findByEmail(String email);

    Optional<Cuenta> findByCedula(String cedula);

}
