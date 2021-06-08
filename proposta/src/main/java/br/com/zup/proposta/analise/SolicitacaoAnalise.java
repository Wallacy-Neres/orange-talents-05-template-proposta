package br.com.zup.proposta.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.zup.proposta.analise.analiseDTO.AnalisePropostaRequest;
import br.com.zup.proposta.analise.analiseDTO.ResultadoAnalise;

@FeignClient(name = "solicitacao", url="http://localhost:9999")
public interface SolicitacaoAnalise {
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/solicitacao", produces = "application/json", consumes = "application/json")
	public ResultadoAnalise solicitacao(AnalisePropostaRequest proposta);
}
