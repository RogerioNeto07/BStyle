package rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.denuncia;

public record DenunciaCreateDTO(
    Long vendedorDenunciadoId,
    String motivo,
    String descricao
) {}