package rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.produto;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public record ProdutoCreateDTO(
    String nome,
    String descricao,
    Double preco,
    Integer quantidade,
    Long tipoId,
    String coresIds,
    String tagsIds,
    List<MultipartFile> fotos
) {}