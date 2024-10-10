package co.edu.uniquindio.unieventos.repositories;

import co.edu.uniquindio.unieventos.model.documents.Carrito;
import co.edu.uniquindio.unieventos.model.documents.Cuenta;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarritoRepo extends MongoRepository<Carrito, String> {

    Optional<Carrito> findByIdCuenta(ObjectId idCuenta);
}
