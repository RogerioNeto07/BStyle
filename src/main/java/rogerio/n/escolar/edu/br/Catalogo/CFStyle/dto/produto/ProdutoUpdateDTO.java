package rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.produto;

import java.util.List;

public class ProdutoUpdateDTO {

    private String nome;
    private String descricao;
    private Double preco;
    private Integer quantidade;
    private Boolean ativo;
    private List<Long> coresIds;
    private List<Long> tagsIds;
    private String fotos;
    private String opcoes;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<Long> getCoresIds() {
        return coresIds;
    }

    public void setCoresIds(List<Long> coresIds) {
        this.coresIds = coresIds;
    }

    public List<Long> getTagsIds() {
        return tagsIds;
    }

    public void setTagsIds(List<Long> tagsIds) {
        this.tagsIds = tagsIds;
    }

    public String getFotos() {
        return fotos;
    }

    public void setFotos(String fotos) {
        this.fotos = fotos;
    }

    public String getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(String opcoes) {
        this.opcoes = opcoes;
    }
}