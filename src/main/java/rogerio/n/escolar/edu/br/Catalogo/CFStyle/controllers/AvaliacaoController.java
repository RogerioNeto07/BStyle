package rogerio.n.escolar.edu.br.Catalogo.CFStyle.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.UsuarioRepository;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.avaliacao.AvaliacaoCreateDTO;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.avaliacao.AvaliacaoResponseDTO;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Usuario;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.services.AvaliacaoService;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registra uma avaliação para um vendedor com envio obrigatório da foto do produto")
    public ResponseEntity<AvaliacaoResponseDTO> criar(
            @ModelAttribute AvaliacaoCreateDTO dto, 
            @RequestParam("foto") MultipartFile foto,
            Authentication authentication) {
            
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Acesso negado: Usuário não autenticado.");
        }

        Usuario compradorLogado = usuarioRepository.findByLogin(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Usuário logado não encontrado"));

        String nomeArquivo = null;
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

                nomeArquivo = UUID.randomUUID().toString() + extensao;
                Path caminhoCompleto = Paths.get(diretorioUpload + nomeArquivo);

                Files.write(caminhoCompleto, foto.getBytes());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        AvaliacaoCreateDTO novoDto = new AvaliacaoCreateDTO(
            dto.vendedorId(),
            compradorLogado.getId(),
            dto.nota(),
            dto.comentario(),
            nomeArquivo
        );

        AvaliacaoResponseDTO response = avaliacaoService.avaliarVendedor(novoDto, compradorLogado);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/vendedor/{vendedorId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lista todas as avaliações recebidas por um vendedor específico")
    public List<AvaliacaoResponseDTO> listarPorVendedor(@PathVariable Long vendedorId) {
        return avaliacaoService.listarPorVendedor(vendedorId);
    }
}