package rogerio.n.escolar.edu.br.Catalogo.CFStyle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.usuario.UsuarioResponseDTO;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.usuario.UsuarioUpdateDTO;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Usuario;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.UsuarioRepository;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        List<UsuarioResponseDTO> usuarios = repository.findAll().stream()
                .map(u -> new UsuarioResponseDTO(
                        u.getId(), u.getLogin(), u.getNome(), u.getTelefone(), 
                        u.getCidade(), u.getEstado(), u.getRua(), u.getNumero(), u.getFotoPerfilUrl()))
                .toList();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/perfil")
    public ResponseEntity<String> atualizarPerfil(@RequestBody UsuarioUpdateDTO dados, Authentication authentication) {
        String loginLogado = authentication.getName();
        Usuario usuario = repository.findByLogin(loginLogado)
                .orElseThrow(() -> new RuntimeException("Usuário logado não encontrado"));

        if (dados.nome() != null) usuario.setNome(dados.nome());
        if (dados.telefone() != null) usuario.setTelefone(dados.telefone());
        if (dados.cidade() != null) usuario.setCidade(dados.cidade());
        if (dados.estado() != null) usuario.setEstado(dados.estado());
        if (dados.rua() != null) usuario.setRua(dados.rua());
        if (dados.numero() != null) usuario.setNumero(dados.numero());
        if (dados.fotoPerfilUrl() != null) usuario.setFotoPerfilUrl(dados.fotoPerfilUrl());

        repository.save(usuario);

        return ResponseEntity.ok("Perfil atualizado com sucesso!");
    }

    @GetMapping("/perfil")
    public ResponseEntity<UsuarioResponseDTO> verMeuPerfil(Authentication authentication) {
        String loginLogado = authentication.getName();
        Usuario usuario = repository.findByLogin(loginLogado)
                .orElseThrow(() -> new RuntimeException("Usuário logado não encontrado"));

        UsuarioResponseDTO dto = new UsuarioResponseDTO(
                usuario.getId(), 
                usuario.getLogin(), 
                usuario.getNome(), 
                usuario.getTelefone(), 
                usuario.getCidade(), 
                usuario.getEstado(), 
                usuario.getRua(), 
                usuario.getNumero(), 
                usuario.getFotoPerfilUrl()
        );
        return ResponseEntity.ok(dto);
    }
}