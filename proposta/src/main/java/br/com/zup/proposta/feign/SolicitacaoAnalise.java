package br.com.zup.proposta.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.zup.proposta.analise.AnalisePropostaRequest;
import br.com.zup.proposta.analise.ResultadoAnalise;

@FeignClient(name = "solicitacao", url="${servico.analise.proposta}")
public interface SolicitacaoAnalise {
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/solicitacao", produces = "application/json", consumes = "application/json")
	public ResultadoAnalise solicitacao(AnalisePropostaRequest proposta);
}
