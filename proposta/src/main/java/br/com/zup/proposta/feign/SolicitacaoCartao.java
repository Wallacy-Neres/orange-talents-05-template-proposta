package br.com.zup.proposta.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.zup.proposta.analise.AnalisePropostaRequest;
import br.com.zup.proposta.cartao.RespostaCartao;

@FeignClient(name = "cartao", url="${servico.recurso.cartao}")
public interface SolicitacaoCartao {

	@RequestMapping(method = RequestMethod.POST, value = "/api/cartoes", consumes = "application/json")
	public RespostaCartao solicitaCartao(AnalisePropostaRequest analisePropostaRequest);
}
