package rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.denuncia;

import java.time.LocalDateTime;

public record DenunciaResponseDTO(
    Long id,
    Long denuncianteId,
    String denuncianteNome,
    Long vendedorDenunciadoId,
    String vendedorDenunciadoNome,
    String motivo,
    String descricao,
    LocalDateTime criadaEm
) {}