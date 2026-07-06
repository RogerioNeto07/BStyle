package rogerio.n.escolar.edu.br.Catalogo.CFStyle.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "faturamento")
public class Faturamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String periodo;
    private Float total_vendas;

    public Faturamento() {
    }

    public Faturamento(Long id, Usuario usuario, String periodo, Float total_vendas) {
        this.id = id;
        this.usuario = usuario;
        this.periodo = periodo;
        this.total_vendas = total_vendas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Float getTotal_vendas() {
        return total_vendas;
    }

    public void setTotal_vendas(Float total_vendas) {
        this.total_vendas = total_vendas;
    }
}