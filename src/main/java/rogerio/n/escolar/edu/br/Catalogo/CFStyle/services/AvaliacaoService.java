package rogerio.n.escolar.edu.br.Catalogo.CFStyle.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.avaliacao.AvaliacaoCreateDTO;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.avaliacao.AvaliacaoResponseDTO;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Avaliacao;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Usuario;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.AvaliacaoRepository;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.UsuarioRepository;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public AvaliacaoResponseDTO avaliarVendedor(AvaliacaoCreateDTO dto, Usuario compradorLogado) {
        if (dto.fotoComprovanteUrl() == null || dto.fotoComprovanteUrl().isBlank()) {
            throw new RuntimeException("A foto do produto obtido é obrigatória para registrar a avaliação");
        }

        Usuario vendedor = usuarioRepository.findById(dto.vendedorId())
                .orElseThrow(() -> new RuntimeException("Vendedor não encontrado"));

        if (compradorLogado.getId().equals(vendedor.getId())) {
            throw new RuntimeException("Você não pode avaliar a si mesmo");
        }

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setVendedor(vendedor);
        avaliacao.setComprador(compradorLogado);
        avaliacao.setNota(dto.nota());
        avaliacao.setComentario(dto.comentario());
        avaliacao.setFotoComprovanteUrl(dto.fotoComprovanteUrl());

        avaliacaoRepository.save(avaliacao);
        return toResponse(avaliacao);
    }

    public List<AvaliacaoResponseDTO> listarPorVendedor(Long vendedorId) {
        return avaliacaoRepository.findByVendedorId(vendedorId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private AvaliacaoResponseDTO toResponse(Avaliacao a) {
        AvaliacaoResponseDTO dto = new AvaliacaoResponseDTO();
        dto.setId(a.getId());
        dto.setNota(a.getNota());
        dto.setComentario(a.getComentario());
        dto.setFotoComprovanteUrl(a.getFotoComprovanteUrl());
        dto.setCriadaEm(a.getCriadaEm());
        
        if (a.getComprador() != null) {
            dto.setCompradorId(a.getComprador().getId());
            dto.setCompradorNome(a.getComprador().getNome());
        }
        return dto;
    }
}