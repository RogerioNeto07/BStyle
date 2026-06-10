package rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByTipoId(Long tipoId);

    @Query("SELECT p FROM Produto p JOIN p.tags t WHERE t.id = :tagId")
    List<Produto> findByTagId(@Param("tagId") Long tagId);

    List<Produto> findByPreco(Double preco);
}
