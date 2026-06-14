package rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.avaliacao;

import java.time.LocalDateTime;

public class AvaliacaoResponseDTO {
    private Long id;
    private Long compradorId;
    private String compradorNome;
    private int nota;
    private String comentario;
    private String fotoComprovanteUrl;
    private LocalDateTime criadaEm;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getCompradorId() { return compradorId; }
    public void setCompradorId(Long compradorId) { this.compradorId = compradorId; }
    public String getCompradorNome() { return compradorNome; }
    public void setCompradorNome(String compradorNome) { this.compradorNome = compradorNome; }
    public int getNota() { return nota; }
    public void setNota(int nota) { this.nota = nota; }
    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
    public String getFotoComprovanteUrl() { return fotoComprovanteUrl; }
    public void setFotoComprovanteUrl(String fotoComprovanteUrl) { this.fotoComprovanteUrl = fotoComprovanteUrl; }
    public LocalDateTime getCriadaEm() { return criadaEm; }
    public void setCriadaEm(LocalDateTime criadaEm) { this.criadaEm = criadaEm; }
}