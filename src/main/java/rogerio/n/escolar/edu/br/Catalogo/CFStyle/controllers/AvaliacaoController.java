package rogerio.n.escolar.edu.br.Catalogo.CFStyle.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registra uma avaliação para um vendedor com envio obrigatório da foto do produto")
    public AvaliacaoResponseDTO criar(@RequestBody AvaliacaoCreateDTO dto, @AuthenticationPrincipal Usuario compradorLogado) {
        return avaliacaoService.avaliarVendedor(dto, compradorLogado);
    }

    @GetMapping("/vendedor/{vendedorId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lista todas as avaliações recebidas por um vendedor específico")
    public List<AvaliacaoResponseDTO> listarPorVendedor(@PathVariable Long vendedorId) {
        return avaliacaoService.listarPorVendedor(vendedorId);
    }
}