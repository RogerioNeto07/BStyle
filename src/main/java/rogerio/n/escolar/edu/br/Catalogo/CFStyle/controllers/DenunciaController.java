package rogerio.n.escolar.edu.br.Catalogo.CFStyle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.denuncia.DenunciaCreateDTO;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Usuario;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.services.DenunciaService;

@RestController
@RequestMapping("/denuncias")
public class DenunciaController {

    @Autowired
    private DenunciaService denunciaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registra uma denúncia diretamente contra o perfil de um vendedor")
    public void criar(@RequestBody DenunciaCreateDTO dto, @AuthenticationPrincipal Usuario denuncianteLogado) {
        denunciaService.denunciarVendedor(dto, denuncianteLogado);
    }
}