package rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
    @NotBlank(message = "O usuário/login é obrigatório.")
    String login,
    
    @NotBlank(message = "A senha é obrigatória.")
    String senha
) {}