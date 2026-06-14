package rogerio.n.escolar.edu.br.Catalogo.CFStyle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.usuario.UsuarioResponseDTO;
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
                .map(u -> new UsuarioResponseDTO(u.getId(), u.getLogin(), u.getNome(), u.getTelefone(), u.getCidade(), u.getEstado()))
                .toList();
        return ResponseEntity.ok(usuarios);
    }
}