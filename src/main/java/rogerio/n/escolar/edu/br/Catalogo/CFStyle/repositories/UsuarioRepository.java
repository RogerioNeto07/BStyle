package rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByLogin(String login);
}