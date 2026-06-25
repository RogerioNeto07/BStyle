package rogerio.n.escolar.edu.br.Catalogo.CFStyle.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    public List<ProdutoResponseDTO> listarMeusProdutos(Usuario vendedorLogado) {
        return produtoRepository.findByVendedorId(vendedorLogado.getId())
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
        produto.setVendedor(vendedorLogado);

        if (dto.fotos() != null && !dto.fotos().isEmpty()) {
            List<String> nomesFotos = new ArrayList<>();
            try {
                File diretorio = new File("uploads");
                if (!diretorio.exists()) {
                    diretorio.mkdirs();
                }
                for (MultipartFile foto : dto.fotos()) {
                    if (!foto.isEmpty()) {
                        String nomeArquivo = foto.getOriginalFilename();
                        Path caminhoDestino = Paths.get("uploads").resolve(nomeArquivo);
                        Files.copy(foto.getInputStream(), caminhoDestino, StandardCopyOption.REPLACE_EXISTING);
                        nomesFotos.add(nomeArquivo);
                    }
                }
            } catch (Exception e) {
                System.out.println("Erro ao salvar arquivo físico: " + e.getMessage());
            }
            produto.setFotos(nomesFotos);
        }

        produto.setTipo(tipoRepository.findById(dto.tipoId())
                .orElseThrow(() -> new RuntimeException("Tipo não encontrado")));

        List<Long> coresLongIds = converterStringListParaLong(dto.coresIds());
        List<Cor> cores = corRepository.findAllById(coresLongIds);
        produto.setCores(cores);

        List<Long> tagsLongIds = converterStringListParaLong(dto.tagsIds());
        List<Tag> tags = tagRepository.findAllById(tagsLongIds);
        produto.setTags(tags);

        produtoRepository.save(produto);

        return toResponse(produto);
    }

    public ProdutoResponseDTO atualizar(Long id, ProdutoCreateDTO dto, Usuario vendedorLogado) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (!produto.getVendedor().getId().equals(vendedorLogado.getId())) {
            throw new RuntimeException("Acesso negado: Você não é o dono deste produto para editá-lo!");
        }

        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setPreco(dto.preco());
        produto.setQuantidade(dto.quantidade());

        if (dto.fotos() != null && !dto.fotos().isEmpty()) {
            List<String> nomesFotos = new ArrayList<>();
            try {
                File diretorio = new File("uploads");
                if (!diretorio.exists()) {
                    diretorio.mkdirs();
                }
                for (MultipartFile foto : dto.fotos()) {
                    if (!foto.isEmpty()) {
                        String nomeArquivo = foto.getOriginalFilename();
                        Path caminhoDestino = Paths.get("uploads").resolve(nomeArquivo);
                        Files.copy(foto.getInputStream(), caminhoDestino, StandardCopyOption.REPLACE_EXISTING);
                        nomesFotos.add(nomeArquivo);
                    }
                }
            } catch (Exception e) {
                System.out.println("Erro ao atualizar arquivo físico: " + e.getMessage());
            }
            produto.setFotos(nomesFotos);
        }

        produto.setTipo(tipoRepository.findById(dto.tipoId())
                .orElseThrow(() -> new RuntimeException("Tipo não encontrado")));

        List<Long> coresLongIds = converterStringListParaLong(dto.coresIds());
        produto.setCores(corRepository.findAllById(coresLongIds));

        List<Long> tagsLongIds = converterStringListParaLong(dto.tagsIds());
        produto.setTags(tagRepository.findAllById(tagsLongIds));

        produtoRepository.save(produto);

        return toResponse(produto);
    }

    public void deletar(Long id, Usuario vendedorLogado) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (!produto.getVendedor().getId().equals(vendedorLogado.getId())) {
            throw new RuntimeException("Acesso negado: Você não é o dono deste produto para excluí-lo!");
        }

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

    private List<Long> converterStringListParaLong(String stringOriginal) {
        if (stringOriginal == null || stringOriginal.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.stream(stringOriginal.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Long::parseLong)
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