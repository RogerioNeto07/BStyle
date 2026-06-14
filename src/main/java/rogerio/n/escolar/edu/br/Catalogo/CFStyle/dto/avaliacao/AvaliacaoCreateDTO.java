package rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.avaliacao;

public record AvaliacaoCreateDTO(
    Long vendedorId,
    Long compradorId,
    int nota,
    String comentario,
    String fotoComprovanteUrl
) {}