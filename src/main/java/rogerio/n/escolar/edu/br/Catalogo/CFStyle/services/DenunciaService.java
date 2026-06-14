package rogerio.n.escolar.edu.br.Catalogo.CFStyle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.denuncia.DenunciaCreateDTO;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Denuncia;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Usuario;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.DenunciaRepository;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.UsuarioRepository;

@Service
public class DenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void denunciarVendedor(DenunciaCreateDTO dto, Usuario denuncianteLogado) {
        Usuario vendedor = usuarioRepository.findById(dto.vendedorDenunciadoId())
                .orElseThrow(() -> new RuntimeException("Vendedor denunciado não encontrado"));

        if (denuncianteLogado.getId().equals(vendedor.getId())) {
            throw new RuntimeException("Você não pode denunciar a si mesmo");
        }

        Denuncia denuncia = new Denuncia();
        denuncia.setDenunciante(denuncianteLogado);
        denuncia.setVendedorDenunciado(vendedor);
        denuncia.setMotivo(dto.motivo());
        denuncia.setDescricao(dto.descricao());

        denunciaRepository.save(denuncia);
    }
}