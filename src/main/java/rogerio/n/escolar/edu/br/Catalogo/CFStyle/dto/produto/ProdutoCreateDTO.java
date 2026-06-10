package rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.produto;
import java.util.List;

public record ProdutoCreateDTO(
    String nome,
    String descricao,
    Double preco,
    int quantidade,
    Long tipoId,
    List<Long> coresIds,
    List<Long> tagsIds,
    List<String> fotos
) {}