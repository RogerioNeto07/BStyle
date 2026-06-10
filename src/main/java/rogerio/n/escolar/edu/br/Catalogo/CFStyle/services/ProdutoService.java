package rogerio.n.escolar.edu.br.Catalogo.CFStyle.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.produto.ProdutoCreateDTO;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.produto.ProdutoResponseDTO;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Cor;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Produto;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Tag;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Usuario;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.CorRepository;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.ProdutoRepository;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.TagRepository;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.TipoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private TipoRepository tipoRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private CorRepository corRepository;

    public List<ProdutoResponseDTO> listar() {
        return produtoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProdutoResponseDTO buscar(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return toResponse(produto);
    }

    public ProdutoResponseDTO criarProduto(ProdutoCreateDTO dto, Usuario vendedorLogado) {
        Produto produto = new Produto();

        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setPreco(dto.preco());
        produto.setQuantidade(dto.quantidade());
        produto.setAtivo(true);
        produto.setFotos(dto.fotos());
        produto.setVendedor(vendedorLogado);

        produto.setTipo(tipoRepository.findById(dto.tipoId())
                .orElseThrow(() -> new RuntimeException("Tipo não encontrado")));

        List<Cor> cores = corRepository.findAllById(dto.coresIds());
        produto.setCores(cores);

        List<Tag> tags = tagRepository.findAllById(dto.tagsIds());
        produto.setTags(tags);

        produtoRepository.save(produto);

        return toResponse(produto);
    }

    public ProdutoResponseDTO atualizar(Long id, ProdutoCreateDTO dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setPreco(dto.preco());
        produto.setQuantidade(dto.quantidade());
        produto.setFotos(dto.fotos());

        produto.setTipo(tipoRepository.findById(dto.tipoId())
                .orElseThrow(() -> new RuntimeException("Tipo não encontrado")));

        produto.setCores(corRepository.findAllById(dto.coresIds()));
        produto.setTags(tagRepository.findAllById(dto.tagsIds()));

        produtoRepository.save(produto);

        return toResponse(produto);
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }

    public List<ProdutoResponseDTO> listarPorTipo(Long tipoId) {
        return produtoRepository.findByTipoId(tipoId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<ProdutoResponseDTO> listarPorTag(Long tagId) {
        return produtoRepository.findByTagId(tagId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<ProdutoResponseDTO> listarDoacoes() {
        return produtoRepository.findByPreco(0.0)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ProdutoResponseDTO toResponse(Produto p) {
        ProdutoResponseDTO dto = new ProdutoResponseDTO();

        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setDescricao(p.getDescricao());
        dto.setPreco(p.getPreco());
        dto.setQuantidade(p.getQuantidade());
        dto.setAtivo(p.isAtivo());
        
        if (p.getFotos() != null && !p.getFotos().isEmpty()) {
            dto.setFotos(p.getFotos().get(0));
        }
        
        dto.setOpcoes(p.getOpcoes());
        dto.setCriadoEm(p.getCriadoEm());
        dto.setAtualizadoEm(p.getAtualizadoEm());

        if (p.getTipo() != null) {
            dto.setTipoId(p.getTipo().getId());
            dto.setTipoNome(p.getTipo().getNome());
        }

        if (p.getCores() != null) {
            dto.setCoresIds(p.getCores().stream().map(Cor::getId).toList());
            dto.setCoresNomes(p.getCores().stream().map(Cor::getNome).toList());
        }

        if (p.getTags() != null) {
            dto.setTagsIds(p.getTags().stream().map(Tag::getId).toList());
            dto.setTagsNomes(p.getTags().stream().map(Tag::getNome).toList());
        }

        if (p.getVendedor() != null) {
            dto.setVendedorId(p.getVendedor().getId());
            dto.setVendedorNome(p.getVendedor().getNome());
            dto.setVendedorTelefone(p.getVendedor().getTelefone());
            dto.setVendedorCidade(p.getVendedor().getCidade());
        }

        return dto;
    }
}