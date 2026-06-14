package rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByVendedorId(Long vendedorId);
}