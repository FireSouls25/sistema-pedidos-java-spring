package co.ucc.pedidos.controller;

import co.ucc.pedidos.model.ProductoModel;
import co.ucc.pedidos.repository.ProductoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @GetMapping
    public List<ProductoModel> listarProductos() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoModel> obtenerProducto(@PathVariable String id) {
        return productoRepository.findByIdProducto(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductoModel> crearProducto(@RequestBody ProductoModel producto) {
        if (producto.getIdProducto() == null || producto.getIdProducto().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productoRepository.save(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoModel> actualizarProducto(
            @PathVariable String id,
            @RequestBody ProductoModel producto) {
        return productoRepository.findByIdProducto(id)
                .map(existing -> {
                    if (producto.getCantidad() > 0) existing.setCantidad(producto.getCantidad());
                    if (producto.getResena() != null) existing.setResena(producto.getResena());
                    return ResponseEntity.ok(productoRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable String id) {
        if (productoRepository.existsByIdProducto(id)) {
            productoRepository.deleteByIdProducto(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}