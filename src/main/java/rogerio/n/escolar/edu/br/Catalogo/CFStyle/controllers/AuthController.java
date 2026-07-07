package rogerio.n.escolar.edu.br.Catalogo.CFStyle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.usuario.UsuarioDTO;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.usuario.UsuarioResponseDTO;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Usuario;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.UsuarioRepository;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping(value = "/registrar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UsuarioResponseDTO> registrar(
            @ModelAttribute UsuarioDTO dados,
            @RequestParam(value = "foto", required = false) MultipartFile foto) {
            
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
                
                novoUsuario.setFotoPerfilUrl(nomeArquivo);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            novoUsuario.setFotoPerfilUrl(null);
        }
        
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

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.usuario.LoginRequestDTO dados) {
    Usuario usuario = repository.findByLogin(dados.login())
            .orElse(null);

    if (usuario == null || !encoder.matches(dados.senha(), usuario.getSenha())) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("{\"erro\": \"Usuário ou senha incorretos.\"}");
    }

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
}