package br.com.zup.proposta.bloqueio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.proposta.cartao.Cartao;

@Repository
public interface BloqueioRepository extends JpaRepository<Bloqueio, Long>{

	Optional<Bloqueio> findByCartao(Cartao cartaoASerBloqueado);

}
