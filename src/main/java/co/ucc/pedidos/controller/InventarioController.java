package co.ucc.pedidos.controller;

import co.ucc.pedidos.model.InventarioModel;
import co.ucc.pedidos.repository.InventarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    private final InventarioRepository inventarioRepository;

    public InventarioController(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    @GetMapping
    public List<InventarioModel> listarInventario() {
        return inventarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioModel> obtenerInventario(@PathVariable String id) {
        return inventarioRepository.findByIdInventario(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InventarioModel> crearInventario(@RequestBody InventarioModel inventario) {
        if (inventario.getIdInventario() == null || inventario.getIdInventario().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(inventarioRepository.save(inventario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioModel> actualizarInventario(
            @PathVariable String id,
            @RequestBody InventarioModel inventario) {
        return inventarioRepository.findByIdInventario(id)
                .map(existing -> {
                    if (inventario.getCantidad() > 0) existing.setCantidad(inventario.getCantidad());
                    existing.setDisponibilidad(inventario.isDisponibilidad());
                    if (inventario.getCategoria() != null) existing.setCategoria(inventario.getCategoria());
                    return ResponseEntity.ok(inventarioRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInventario(@PathVariable String id) {
        if (inventarioRepository.existsByIdInventario(id)) {
            inventarioRepository.deleteByIdInventario(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}