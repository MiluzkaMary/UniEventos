package co.edu.uniquindio.unieventos.services.impl;

import co.edu.uniquindio.unieventos.dto.evento.InformacionEventoDTO;
import co.edu.uniquindio.unieventos.dto.evento.LocalidadDTO;
import co.edu.uniquindio.unieventos.dto.orden.*;
import co.edu.uniquindio.unieventos.model.documents.Cuenta;
import co.edu.uniquindio.unieventos.model.documents.Evento;
import co.edu.uniquindio.unieventos.model.documents.Orden;
import co.edu.uniquindio.unieventos.model.enums.Pasarela;
import co.edu.uniquindio.unieventos.model.vo.DetalleOrden;
import co.edu.uniquindio.unieventos.model.vo.Localidad;
import co.edu.uniquindio.unieventos.model.vo.Pago;
import co.edu.uniquindio.unieventos.repositories.OrdenRepo;
import co.edu.uniquindio.unieventos.services.interfaces.EventoServicio;
import co.edu.uniquindio.unieventos.services.interfaces.OrdenServicio;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.payment.Payment;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mercadopago.resources.preference.Preference;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class OrdenServicioImpl implements OrdenServicio {

    private final OrdenRepo ordenRepo;

    private final EventoServicio eventoServicio;

    @Override
    public String crearOrden(CrearOrdenDTO ordenDTO) throws Exception {
        // Verificar si ya existe una orden con el mismo código QR
        if (existeOrdenPorCodigoQR(ordenDTO.codigoQR())) {
            throw new Exception("El código QR " + ordenDTO.codigoQR() + " ya está en uso.");
        }

        // Crear un nuevo objeto Pago usando los datos de ordenDTO
        Pago nuevoPago = new Pago(
                ordenDTO.pago().codigo(),                   // Código del pago
                ordenDTO.pago().fecha(),                    // Fecha del pago
                ordenDTO.pago().estadoPago(),               // Estado del pago
                ordenDTO.pago().detalleEstado(),            // Detalle del estado del pago
                ordenDTO.pago().metodoPago(),               // Método de pago
                ordenDTO.pago().moneda(),                   // Moneda
                ordenDTO.pago().codigoAutorizacion(),       // Código de autorización
                ordenDTO.pago().total()                      // Total del pago
        );

        // Crear una nueva orden
        Orden nuevaOrden = Orden.builder()
                .idCuenta(ordenDTO.idCuenta())               // Asignar ID de cuenta
                .idCupon(ordenDTO.idCupon() != null ? ordenDTO.idCupon() : null) // Asignar ID de cupón si existe
                .pago(nuevoPago)                             // Mapear pago desde DTO
                .fecha(ordenDTO.fecha() != null ? ordenDTO.fecha() : LocalDateTime.now()) // Fecha actual o proporcionada
                .codigoQR(ordenDTO.codigoQR())              // Código QR de la orden
                .items(ordenDTO.items().stream()             // Mapear los detalles a la entidad DetalleOrden
                        .map(detalle -> new DetalleOrden(
                                detalle.id(),                // ID del producto o evento
                                detalle.idEvento(),          // ID del evento
                                detalle.cantidad(),          // Cantidad de productos
                                detalle.nombreLocalidad(),    // Nombre de la localidad o producto
                                detalle.total(),
                                detalle.precioUnitario()))   // Precio unitario
                        .toList())                       // Convertir a lista de DetalleOrden
                .total(ordenDTO.total())                     // Asignar el total de la orden
                .codigoPasarela(ordenDTO.codigoPasarela())   // Asignar código de pasarela
                .build();

        // Guardar la orden en la base de datos
        Orden ordenCreada = ordenRepo.save(nuevaOrden);

        return ordenCreada.getId(); // Retornar el ID de la orden creada
    }



    @Override
    public void editarOrden(EditarOrdenDTO ordenDTO) throws Exception {
        // Buscar la orden existente por ID
        Orden ordenExistente = ordenRepo.findById(ordenDTO.id())
                .orElseThrow(() -> new Exception("Orden no encontrada"));

        // Actualizar los atributos de la orden existente
        ordenExistente.setIdCuenta(ordenDTO.idCuenta());
        ordenExistente.setIdCupon(ordenDTO.idCupon() != null ? ordenDTO.idCupon() : null);

        // Crear un nuevo objeto Pago con todos los atributos necesarios
        Pago nuevoPago = new Pago(
                ordenDTO.pago().codigo(),                // Código del pago
                ordenDTO.pago().fecha(),                 // Fecha del pago
                ordenDTO.pago().estadoPago(),            // Estado del pago
                ordenDTO.pago().detalleEstado(),         // Detalle del estado del pago
                ordenDTO.pago().metodoPago(),            // Método de pago
                ordenDTO.pago().moneda(),                // Moneda
                ordenDTO.pago().codigoAutorizacion(),    // Código de autorización
                ordenDTO.pago().total()                   // Total del pago
        );
        ordenExistente.setPago(nuevoPago);

        ordenExistente.setFecha(ordenDTO.fecha());
        ordenExistente.setCodigoQR(ordenDTO.codigoQR());
        ordenExistente.setItems(ordenDTO.items().stream()
                .map(detalle -> new DetalleOrden(
                        detalle.id(),           // ID del producto o evento
                        detalle.idEvento(),     // ID del evento
                        detalle.cantidad(),     // Cantidad de productos
                        detalle.nombreLocalidad(), // Nombre del producto o localidad
                        detalle.total(),        // Total
                        detalle.precioUnitario() // Precio unitario
                ))
                .toList());
        ordenExistente.setTotal(ordenDTO.total());

        // Guardar la orden actualizada en la base de datos
        ordenRepo.save(ordenExistente);
    }


    @Override
    public void eliminarOrden(String id) throws Exception {
        // Buscar la orden existente por ID
        Orden ordenExistente = ordenRepo.findById(id)
                .orElseThrow(() -> new Exception("Orden no encontrada"));

        // Eliminar la orden de la base de datos
        ordenRepo.delete(ordenExistente);
    }

    @Override
    public InformacionOrdenDTO obtenerInformacionOrden(String id) throws Exception {
        // Buscar la orden existente por ID
        Orden ordenExistente = ordenRepo.findById(id)
                .orElseThrow(() -> new Exception("Orden no encontrada"));

        // Mapear la orden a InformacionOrdenDTO
        return new InformacionOrdenDTO(
                ordenExistente.getId(),
                ordenExistente.getIdCuenta(),
                ordenExistente.getIdCupon(),
                ordenExistente.getPago(),
                ordenExistente.getFecha(),
                ordenExistente.getCodigoQR(),
                ordenExistente.getItems().stream()
                        .map(detalle -> new DetalleOrden(
                                detalle.getId(),
                                detalle.getIdEvento(),
                                detalle.getCantidad(),
                                detalle.getNombreLocalidad(),
                                detalle.getPrecio(),
                                detalle.getPrecioUnitario() ))
                        .toList(),
                ordenExistente.getTotal(),
                ordenExistente.getCodigoPasarela()
        );
    }

    @Override
    public List<ItemOrdenDTO> listarOrdenes() {
        // Obtener todas las órdenes de la base de datos
        List<Orden> ordenes = ordenRepo.findAll();

        // Mapear las órdenes a una lista de ItemOrdenDTO
        return ordenes.stream()
                .map(orden -> new ItemOrdenDTO(
                        orden.getId(),                           // ID de la orden
                        orden.getIdCuenta(),                     // ID de la cuenta
                        orden.getIdCupon(),                      // ID del cupón
                        orden.getFecha(),                        // Fecha de la orden
                        orden.getItems().stream()                // Mapeo de los detalles de la orden a DetalleOrdenDTO
                                .map(detalle -> new DetalleOrdenDTO(
                                        detalle.getId(),          // ID del detalle
                                        detalle.getIdEvento(),
                                        detalle.getCantidad(),
                                        detalle.getNombreLocalidad(),
                                        detalle.getPrecio(),
                                        detalle.getPrecioUnitario()))
                                .toList(),                       // Convertir a lista de DetalleOrdenDTO
                        orden.getTotal()                         // Total de la orden
                ))
                .toList();  // Convertir el resultado final a una lista de ItemOrdenDTO
    }

    private boolean existeOrdenPorCuentaId(ObjectId idCuenta) {
        return ordenRepo.findByIdCuenta(idCuenta).isPresent();
    }

    private boolean existeOrdenPorCodigoQR(String codigoQR) {
        return ordenRepo.findByCodigoQR(codigoQR).isPresent();
    }

    @Override
    public Preference realizarPago(String idOrden) throws Exception {

        Orden ordenGuardada = ordenRepo.findById(idOrden)
                .orElseThrow(() -> new Exception("Orden no encontrada"));

        List<PreferenceItemRequest> itemsPasarela = new ArrayList<>();


        // Recorrer los items de la orden y crea los ítems de la pasarela
        for(DetalleOrden item : ordenGuardada.getItems()){


            // Obtener el evento y la localidad del ítem
            InformacionEventoDTO evento = eventoServicio.obtenerInformacionEvento(item.getIdEvento().toString());
            Localidad localidad = evento.localidades().stream()
                    .filter(localidad1 -> localidad1.getNombre().equals(item.getNombreLocalidad())) // Filtrar por nombre de localidad
                    .findFirst() // Obtener el primer resultado que coincida
                    .orElseThrow(() -> new Exception("Localidad no encontrada para el evento"));

            // Crear el item de la pasarela
            PreferenceItemRequest itemRequest =
                    PreferenceItemRequest.builder()
                            .id(evento.id())
                            .title(evento.nombre())
                            .pictureUrl(evento.imagenPortada())
                            .categoryId(evento.tipo().name())
                            .quantity(item.getCantidad())
                            .currencyId("COP")
                            .unitPrice(BigDecimal.valueOf(localidad.getPrecio()))
                            .build();


            itemsPasarela.add(itemRequest);
        }


        // Configurar las credenciales de MercadoPago
        MercadoPagoConfig.setAccessToken("ACCESS_TOKEN");


        // Configurar las urls de retorno de la pasarela (Frontend)
        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success("URL PAGO EXITOSO")
                .failure("URL PAGO FALLIDO")
                .pending("URL PAGO PENDIENTE")
                .build();


        // Construir la preferencia de la pasarela con los ítems, metadatos y urls de retorno
        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .backUrls(backUrls)
                .items(itemsPasarela)
                .metadata(Map.of("id_orden", ordenGuardada.getId()))
                .notificationUrl("URL NOTIFICACION")
                .build();


        // Crear la preferencia en la pasarela de MercadoPago
        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);


        // Guardar el código de la pasarela en la orden
        ordenGuardada.setCodigoPasarela( preference.getId() );
        ordenRepo.save(ordenGuardada);


        return preference;
    }

    @Override
    public void recibirNotificacionMercadoPago(Map<String, Object> request) {
        try {


            // Obtener el tipo de notificación
            Object tipo = request.get("type");


            // Si la notificación es de un pago entonces obtener el pago y la orden asociada
            if ("payment".equals(tipo)) {


                // Capturamos el JSON que viene en el request y lo convertimos a un String
                String input = request.get("data").toString();


                // Extraemos los números de la cadena, es decir, el id del pago
                String idPago = input.replaceAll("\\D+", "");


                // Se crea el cliente de MercadoPago y se obtiene el pago con el id
                PaymentClient client = new PaymentClient();
                Payment payment = client.get( Long.parseLong(idPago) );


                // Obtener el id de la orden asociada al pago que viene en los metadatos
                String idOrden = payment.getMetadata().get("id_orden").toString();


                // Se obtiene la orden guardada en la base de datos y se le asigna el pago
                Orden ordenGuardada = ordenRepo.findById(idOrden)
                        .orElseThrow(() -> new Exception("Orden no encontrada"));
                Pago pago = crearPago(payment);
                ordenGuardada.setPago(pago);
                ordenRepo.save(ordenGuardada);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Pago crearPago(Payment payment) {
        Pago pago = Pago.builder()
                .codigo(payment.getId().toString())
                .fecha(payment.getDateCreated() != null ? payment.getDateCreated().toLocalDateTime() : LocalDateTime.now())
                .estado(payment.getStatus())
                .detalleEstado(payment.getStatusDetail())
                .metodoPago(Pasarela.MercadoPago)
                .moneda(payment.getCurrencyId())
                .codigoAutorizacion(payment.getAuthorizationCode())
                .totalPago(payment.getTransactionAmount() != null ? payment.getTransactionAmount().floatValue() : 0f)
                .build();

        return pago;
    }




}
