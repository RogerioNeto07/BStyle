package rogerio.n.escolar.edu.br.Catalogo.CFStyle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.usuario.UsuarioDTO;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.usuario.UsuarioResponseDTO;
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
    public ResponseEntity<UsuarioResponseDTO> registrar(@RequestBody UsuarioDTO dados) {
        if (repository.findByLogin(dados.login()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        
        Usuario novoUsuario = new Usuario();
        novoUsuario.setLogin(dados.login());
        novoUsuario.setSenha(encoder.encode(dados.senha()));
        novoUsuario.setNome(dados.nome());
        novoUsuario.setTelefone(dados.telefone());
        novoUsuario.setCidade(dados.cidade());
        novoUsuario.setEstado(dados.estado());
        novoUsuario.setRua(dados.rua());
        novoUsuario.setNumero(dados.numero());
        novoUsuario.setFotoPerfilUrl(dados.fotoPerfilUrl());
        
        repository.save(novoUsuario);

        UsuarioResponseDTO response = new UsuarioResponseDTO(
            novoUsuario.getId(),
            novoUsuario.getLogin(),
            novoUsuario.getNome(),
            novoUsuario.getTelefone(),
            novoUsuario.getCidade(),
            novoUsuario.getEstado(),
            novoUsuario.getRua(),
            novoUsuario.getNumero(),
            novoUsuario.getFotoPerfilUrl()
        );
        
        return ResponseEntity.ok(response);
    }
}