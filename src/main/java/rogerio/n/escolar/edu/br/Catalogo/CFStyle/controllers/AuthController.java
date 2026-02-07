package rogerio.n.escolar.edu.br.Catalogo.CFStyle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.usuario.UsuarioDTO;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Usuario;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.UsuarioRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody UsuarioDTO dados) {
        if (repository.findByLogin(dados.login()).isPresent()) {
            return ResponseEntity.badRequest().body("Erro: Usuário já cadastrado!");
        }
        Usuario novoUsuario = new Usuario();
        novoUsuario.setLogin(dados.login());
        
        novoUsuario.setSenha(encoder.encode(dados.senha()));
        
        repository.save(novoUsuario);
        
        return ResponseEntity.ok("Usuário vendedora cadastrado com sucesso!");
    }
}