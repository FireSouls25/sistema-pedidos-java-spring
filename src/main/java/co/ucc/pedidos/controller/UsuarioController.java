package co.ucc.pedidos.controller;

import co.ucc.pedidos.model.UsuarioModel;
import co.ucc.pedidos.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        
        if (usuarioService.autenticar(username, password)) {
            return ResponseEntity.ok(Map.of("mensaje", "Login exitoso", "username", username));
        }
        return ResponseEntity.status(401).body(Map.of("mensaje", "Credenciales incorrectas"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        
        try {
            UsuarioModel usuario = usuarioService.registrar(username, password);
            return ResponseEntity.ok(Map.of("mensaje", "Usuario registrado", "id", usuario.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", e.getMessage()));
        }
    }
}