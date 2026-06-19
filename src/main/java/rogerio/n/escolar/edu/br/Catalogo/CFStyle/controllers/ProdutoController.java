package rogerio.n.escolar.edu.br.Catalogo.CFStyle.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.produto.ProdutoCreateDTO;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.produto.ProdutoResponseDTO;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Usuario;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.services.ProdutoService;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.UsuarioRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    @Operation(summary = "Lista todos os produtos")
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoResponseDTO> listar() {
        return produtoService.listar();
    }

    @GetMapping("/meus-produtos")
    @Operation(summary = "Lista apenas os produtos pertencentes ao usuário logado")
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoResponseDTO> listarMeusProdutos(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Acesso negado: Usuário não autenticado.");
        }
        Usuario logado = usuarioRepository.findByLogin(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Usuário logado não encontrado"));
        return produtoService.listarMeusProdutos(logado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um produto por ID")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoResponseDTO buscar(@PathVariable Long id) {
        return produtoService.buscar(id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "basicAuth")
    @Operation(
        summary = "Cria um produto associando um arquivo de imagem real e vinculando ao vendedor",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @io.swagger.v3.oas.annotations.media.Content(
                mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ProdutoCreateDTO.class)
            )
        )
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponseDTO criar(
            @ModelAttribute ProdutoCreateDTO dto, 
            Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Acesso negado: Usuário não autenticado.");
        }
        Usuario logado = usuarioRepository.findByLogin(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Usuário logado não encontrado"));
        return produtoService.criarProduto(dto, logado);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Atualiza um produto e suas imagens se pertencer ao usuário logado")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoResponseDTO atualizar(@PathVariable Long id,
                                        @ModelAttribute ProdutoCreateDTO dto,
                                        Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Acesso negado: Usuário não autenticado.");
        }
        Usuario logado = usuarioRepository.findByLogin(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Usuário logado não encontrado"));
        return produtoService.atualizar(id, dto, logado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um produto se ele pertencer ao usuário logado")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id,
                        Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Acesso negado: Usuário não autenticado.");
        }
        Usuario logado = usuarioRepository.findByLogin(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Usuário logado não encontrado"));
        produtoService.deletar(id, logado);
    }

    @GetMapping("/tipo/{tipoId}")
    @Operation(summary = "Busca produtos por tipo")
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoResponseDTO> listarPorTipo(@PathVariable Long tipoId) {
        return produtoService.listarPorTipo(tipoId);
    }

    @GetMapping("/tag/{tagId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca produtos por tag")
    public List<ProdutoResponseDTO> listarPorTag(@PathVariable Long tagId) {
        return produtoService.listarPorTag(tagId);
    }

    @GetMapping("/doacoes")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lista apenas desapegos destinados a doação (preço igual a zero)")
    public List<ProdutoResponseDTO> listarDoacoes() {
        return produtoService.listarDoacoes();
    }
}