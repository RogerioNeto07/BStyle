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
import lombok.Data;

@Data
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
}