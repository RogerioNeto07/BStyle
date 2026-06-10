package rogerio.n.escolar.edu.br.Catalogo.CFStyle.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // -----------------------------
    // LISTAR TODOS
    // -----------------------------
    @GetMapping
    @Operation(summary = "Lista todos os produtos")
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoResponseDTO> listar() {
        return produtoService.listar();
    }

    // -----------------------------
    // BUSCAR POR ID
    // -----------------------------
    @GetMapping("/{id}")
    @Operation(summary = "Busca um produto por ID")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoResponseDTO buscar(@PathVariable Long id) {
        return produtoService.buscar(id);
    }

    // -----------------------------
    // CRIAR PRODUTO (VINCULADO AO USUÁRIO LOGADO)
    // -----------------------------
    @PostMapping
    @Operation(summary = "Cria um produto associando automaticamente ao vendedor logado")
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponseDTO criar(@RequestBody ProdutoCreateDTO dto, 
                                    @AuthenticationPrincipal Usuario vendedorLogado) {
        return produtoService.criarProduto(dto, vendedorLogado);
    }

    // -----------------------------
    // ATUALIZAR PRODUTO
    // -----------------------------
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um produto")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoResponseDTO atualizar(@PathVariable Long id,
                                        @RequestBody ProdutoCreateDTO dto) {
        return produtoService.atualizar(id, dto);
    }

    // -----------------------------
    // DELETAR PRODUTO
    // -----------------------------
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um produto")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        produtoService.deletar(id);
    }

    // -----------------------------
    // BUSCAR POR TIPO
    // -----------------------------
    @GetMapping("/tipo/{tipoId}")
    @Operation(summary = "Busca produtos por tipo")
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoResponseDTO> listarPorTipo(@PathVariable Long tipoId) {
        return produtoService.listarPorTipo(tipoId);
    }

    // -----------------------------
    // BUSCAR POR TAG
    // -----------------------------
    @GetMapping("/tag/{tagId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca produtos por tag")
    public List<ProdutoResponseDTO> listarPorTag(@PathVariable Long tagId) {
        return produtoService.listarPorTag(tagId);
    }

    // -----------------------------
    // NOVO: FILTRAR POR DOAÇÕES (PREÇO == 0)
    // -----------------------------
    @GetMapping("/doacoes")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lista apenas desapegos destinados a doação (preço igual a zero)")
    public List<ProdutoResponseDTO> listarDoacoes() {
        return produtoService.listarDoacoes();
    }
}