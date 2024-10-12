package co.edu.uniquindio.unieventos.controllers;

import co.edu.uniquindio.unieventos.dto.cupon.CrearCuponDTO;
import co.edu.uniquindio.unieventos.dto.cupon.EditarCuponDTO;
import co.edu.uniquindio.unieventos.dto.cupon.InformacionCuponDTO;
import co.edu.uniquindio.unieventos.dto.cupon.ItemCuponDTO;
import co.edu.uniquindio.unieventos.dto.orden.InformacionOrdenDTO;
import co.edu.uniquindio.unieventos.dto.otros.MensajeDTO;
import co.edu.uniquindio.unieventos.services.interfaces.CuponServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cupon")
public class CuponController {

    private final CuponServicio cuponServicio;

    @PostMapping("/crear-cupon")
    public ResponseEntity<MensajeDTO<String>> crearCupon(@Valid @RequestBody CrearCuponDTO cupon) throws Exception {
        cuponServicio.crearCupon(cupon);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cupon creado exitosamente"));
    }

    @PutMapping("/editar-cupon")
    public ResponseEntity<MensajeDTO<String>> editarCupon(@Valid @RequestBody EditarCuponDTO cupon) throws Exception {
        cuponServicio.editarCupon(cupon);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cupon editado exitosamente"));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarCupon(@PathVariable String id) throws Exception {
        cuponServicio.eliminarCupon(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cupon eliminado exitosamente"));
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<MensajeDTO<InformacionCuponDTO>> obtenerCupon(@PathVariable String id) throws Exception {
        InformacionCuponDTO cupon = cuponServicio.obtenerCupon(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, cupon));
    }

    @GetMapping("/listar-todo")
    public ResponseEntity<MensajeDTO<List<ItemCuponDTO>>> listarCupones() {
        List<ItemCuponDTO> lista = cuponServicio.obtenerTodosLosCupones();
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

}
