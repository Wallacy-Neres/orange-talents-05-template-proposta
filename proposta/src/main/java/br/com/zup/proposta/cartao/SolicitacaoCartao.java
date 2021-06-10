package br.com.zup.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.zup.proposta.analise.AnalisePropostaRequest;

@FeignClient(name = "cartao", url="http://localhost:8888")
public interface SolicitacaoCartao {

	@RequestMapping(method = RequestMethod.POST, value = "/api/cartoes", consumes = "application/json")
	public RespostaCartao solicitaCartao(AnalisePropostaRequest analisePropostaRequest);
}
