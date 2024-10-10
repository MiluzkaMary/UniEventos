package co.edu.uniquindio.unieventos.repositories;

import co.edu.uniquindio.unieventos.model.documents.Cuenta;
import co.edu.uniquindio.unieventos.model.documents.Orden;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdenRepo extends MongoRepository<Orden, String> {

    Optional<Orden> findByCuenta(ObjectId idCuenta);

    Optional<Orden> findByCodigoQR(String codigoQR);

}
