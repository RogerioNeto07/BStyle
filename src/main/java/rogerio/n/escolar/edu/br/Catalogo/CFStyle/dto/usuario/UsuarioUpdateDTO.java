package rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.usuario;

public record UsuarioUpdateDTO(
    String nome,
    String telefone,
    String cidade,
    String estado,
    String rua,
    String numero,
    String fotoPerfilUrl
) {}