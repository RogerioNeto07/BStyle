package rogerio.n.escolar.edu.br.Catalogo.CFStyle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.usuario.UsuarioResponseDTO;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.usuario.UsuarioUpdateDTO;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Usuario;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.UsuarioRepository;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

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

    @PutMapping(value = "/perfil", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UsuarioResponseDTO> atualizarPerfil(
            @ModelAttribute UsuarioUpdateDTO dados, 
            @RequestParam(value = "foto", required = false) MultipartFile foto,
            Authentication authentication) {
            
        String loginLogado = authentication.getName();
        Usuario usuario = repository.findByLogin(loginLogado)
                .orElseThrow(() -> new RuntimeException("Usuário logado não encontrado"));

        if (dados.nome() != null) usuario.setNome(dados.nome());
        if (dados.telefone() != null) usuario.setTelefone(dados.telefone());
        if (dados.cidade() != null) usuario.setCidade(dados.cidade());
        if (dados.estado() != null) usuario.setEstado(dados.estado());
        if (dados.rua() != null) usuario.setRua(dados.rua());
        if (dados.numero() != null) usuario.setNumero(dados.numero());

        if (foto != null && !foto.isEmpty()) {
            try {
                String diretorioUpload = "./uploads/";
                File pasta = new File(diretorioUpload);
                if (!pasta.exists()) {
                    pasta.mkdirs();
                }

                String nomeOriginal = foto.getOriginalFilename();
                String extensao = (nomeOriginal != null && nomeOriginal.contains(".")) 
                        ? nomeOriginal.substring(nomeOriginal.lastIndexOf(".")) 
                        : ".jpg";

                String nomeArquivo = UUID.randomUUID().toString() + extensao;
                Path caminhoCompleto = Paths.get(diretorioUpload + nomeArquivo);

                Files.write(caminhoCompleto, foto.getBytes());
                
                usuario.setFotoPerfilUrl(nomeArquivo);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        repository.save(usuario);

        UsuarioResponseDTO response = new UsuarioResponseDTO(
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

        return ResponseEntity.ok(response);
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

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor não encontrado com o ID: " + id));

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