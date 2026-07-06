package rogerio.n.escolar.edu.br.Catalogo.CFStyle.models;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "denuncia")
public class Denuncia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "denunciante_id", nullable = false)
    private Usuario denunciante;

    @ManyToOne
    @JoinColumn(name = "vendedor_denunciado_id", nullable = false)
    private Usuario vendedorDenunciado;

    @Column(nullable = false)
    private String motivo;

    @Column(nullable = false)
    private String descricao;

    @Column(name = "criada_em")
    private LocalDateTime criadaEm;

    @PrePersist
    public void onCreate() {
        criadaEm = LocalDateTime.now();
    }

    public Denuncia() {
    }

    public Denuncia(Long id, Usuario denunciante, Usuario vendedorDenunciado, String motivo, String descricao, LocalDateTime criadaEm) {
        this.id = id;
        this.denunciante = denunciante;
        this.vendedorDenunciado = vendedorDenunciado;
        this.motivo = motivo;
        this.descricao = descricao;
        this.criadaEm = criadaEm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getDenunciante() {
        return denunciante;
    }

    public void setDenunciante(Usuario denunciante) {
        this.denunciante = denunciante;
    }

    public Usuario getVendedorDenunciado() {
        return vendedorDenunciado;
    }

    public void setVendedorDenunciado(Usuario vendedorDenunciado) {
        this.vendedorDenunciado = vendedorDenunciado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getCriadaEm() {
        return criadaEm;
    }

    public void setCriadaEm(LocalDateTime criadaEm) {
        this.criadaEm = criadaEm;
    }
}