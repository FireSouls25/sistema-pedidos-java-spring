package co.ucc.pedidos.service;

import co.ucc.pedidos.model.UsuarioModel;
import co.ucc.pedidos.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean autenticar(String username, String password) {
        Optional<UsuarioModel> usuario = usuarioRepository.findByUsername(username);
        return usuario.isPresent() && passwordEncoder.matches(password, usuario.get().getPassword());
    }

    public UsuarioModel registrar(String username, String password) {
        if (usuarioRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }
        UsuarioModel nuevoUsuario = new UsuarioModel();
        nuevoUsuario.setUsername(username);
        nuevoUsuario.setPassword(passwordEncoder.encode(password));
        return usuarioRepository.save(nuevoUsuario);
    }

    public Optional<UsuarioModel> buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}