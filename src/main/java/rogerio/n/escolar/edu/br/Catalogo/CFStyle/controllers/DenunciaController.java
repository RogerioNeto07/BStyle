package rogerio.n.escolar.edu.br.Catalogo.CFStyle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.denuncia.DenunciaCreateDTO;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.denuncia.DenunciaResponseDTO;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Usuario;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.DenunciaRepository;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.services.DenunciaService;

import java.util.List;

@RestController
@RequestMapping("/denuncias")
public class DenunciaController {

    @Autowired
    private DenunciaService denunciaService;

    @Autowired
    private DenunciaRepository denunciaRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registra uma denúncia diretamente contra o perfil de um vendedor")
    public void criar(@RequestBody DenunciaCreateDTO dto, @AuthenticationPrincipal Usuario denuncianteLogado) {
        denunciaService.denunciarVendedor(dto, denuncianteLogado);
    }

    @GetMapping
    @Operation(summary = "Lista todas as denúncias cadastradas no sistema")
    public ResponseEntity<List<DenunciaResponseDTO>> listarTodas() {
        List<DenunciaResponseDTO> lista = denunciaRepository.findAll().stream()
                .map(d -> new DenunciaResponseDTO(
                        d.getId(),
                        d.getDenunciante().getId(),
                        d.getDenunciante().getNome(),
                        d.getVendedorDenunciado().getId(),
                        d.getVendedorDenunciado().getNome(),
                        d.getMotivo(),
                        d.getDescricao(),
                        d.getCriadaEm()
                ))
                .toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/vendedor/{vendedorId}")
    @Operation(summary = "Lista todas as denúncias recebidas por um vendedor específico")
    public ResponseEntity<List<DenunciaResponseDTO>> buscarPorVendedor(@PathVariable Long vendedorId) {
        List<DenunciaResponseDTO> lista = denunciaRepository.findByVendedorDenunciadoId(vendedorId).stream()
                .map(d -> new DenunciaResponseDTO(
                        d.getId(),
                        d.getDenunciante().getId(),
                        d.getDenunciante().getNome(),
                        d.getVendedorDenunciado().getId(),
                        d.getVendedorDenunciado().getNome(),
                        d.getMotivo(),
                        d.getDescricao(),
                        d.getCriadaEm()
                ))
                .toList();
        return ResponseEntity.ok(lista);
    }
}