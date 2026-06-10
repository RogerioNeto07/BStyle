package rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.produto;

import java.time.LocalDateTime;
import java.util.List;

public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private int quantidade;
    private boolean ativo;
    private String fotos;
    private String opcoes;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    private Long tipoId;
    private String tipoNome;
    private List<Long> coresIds;
    private List<String> coresNomes;
    private List<Long> tagsIds;
    private List<String> tagsNomes;
    private Long vendedorId;
    private String vendedorNome;
    private String vendedorTelefone;
    private String vendedorCidade;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
    public String getFotos() { return fotos; }
    public void setFotos(String fotos) { this.fotos = fotos; }
    public String getOpcoes() { return opcoes; }
    public void setOpcoes(String opcoes) { this.opcoes = opcoes; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
    public LocalDateTime getAtualizadoEm() { return atualizadoEm; }
    public void setAtualizadoEm(LocalDateTime atualizadoEm) { this.atualizadoEm = atualizadoEm; }
    public Long getTipoId() { return tipoId; }
    public void setTipoId(Long tipoId) { this.tipoId = tipoId; }
    public String getTipoNome() { return tipoNome; }
    public void setTipoNome(String tipoNome) { this.tipoNome = tipoNome; }
    public List<Long> getCoresIds() { return coresIds; }
    public void setCoresIds(List<Long> coresIds) { this.coresIds = coresIds; }
    public List<String> getCoresNomes() { return coresNomes; }
    public void setCoresNomes(List<String> coresNomes) { this.coresNomes = coresNomes; }
    public List<Long> getTagsIds() { return tagsIds; }
    public void setTagsIds(List<Long> tagsIds) { this.tagsIds = tagsIds; }
    public List<String> getTagsNomes() { return tagsNomes; }
    public void setTagsNomes(List<String> tagsNomes) { this.tagsNomes = tagsNomes; }
    public Long getVendedorId() { return vendedorId; }
    public void setVendedorId(Long vendedorId) { this.vendedorId = vendedorId; }
    public String getVendedorNome() { return vendedorNome; }
    public void setVendedorNome(String vendedorNome) { this.vendedorNome = vendedorNome; }
    public String getVendedorTelefone() { return vendedorTelefone; }
    public void setVendedorTelefone(String vendedorTelefone) { this.vendedorTelefone = vendedorTelefone; }
    public String getVendedorCidade() { return vendedorCidade; }
    public void setVendedorCidade(String vendedorCidade) { this.vendedorCidade = vendedorCidade; }
}