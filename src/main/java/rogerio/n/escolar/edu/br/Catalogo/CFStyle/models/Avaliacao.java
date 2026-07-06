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
@Table(name = "avaliacao")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Usuario vendedor;

    @ManyToOne
    @JoinColumn(name = "comprador_id", nullable = false)
    private Usuario comprador;

    @Column(nullable = false)
    private int nota;

    @Column(nullable = false)
    private String comentario;

    @Column(name = "foto_comprovante_url", nullable = false)
    private String fotoComprovanteUrl;

    @Column(name = "criada_em")
    private LocalDateTime criadaEm;

    @PrePersist
    public void onCreate() {
        criadaEm = LocalDateTime.now();
    }

    public Avaliacao() {
    }

    public Avaliacao(Long id, Usuario vendedor, Usuario comprador, int nota, String comentario, String fotoComprovanteUrl, LocalDateTime criadaEm) {
        this.id = id;
        this.vendedor = vendedor;
        this.comprador = comprador;
        this.nota = nota;
        this.comentario = comentario;
        this.fotoComprovanteUrl = fotoComprovanteUrl;
        this.criadaEm = criadaEm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFotoComprovanteUrl() {
        return fotoComprovanteUrl;
    }

    public void setFotoComprovanteUrl(String fotoComprovanteUrl) {
        this.fotoComprovanteUrl = fotoComprovanteUrl;
    }

    public LocalDateTime getCriadaEm() {
        return criadaEm;
    }

    public void setCriadaEm(LocalDateTime criadaEm) {
        this.criadaEm = criadaEm;
    }
}