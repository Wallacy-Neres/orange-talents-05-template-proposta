package br.com.zup.proposta.proposta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long>{

	@Query("Select d from Proposta d where d.documento =:documentoParam")
	public Optional<Proposta> findByDocumento(@Param("documentoParam") String documento);
	
	@Query(value = "select * from proposta p where p.estado_proposta = 'ELEGIVEL' and isnull(p.cartao_id)", nativeQuery = true)
	public List<Proposta> FindByPropostasCartaoNull();

}
