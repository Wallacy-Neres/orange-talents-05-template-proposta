package br.com.zup.proposta.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.zup.proposta.bloqueio.BloqueioStatusRequest;
import br.com.zup.proposta.bloqueio.ResultadoStatusCartao;

@FeignClient(name = "cartaoBloqueio", url="${servico.recurso.cartao}")
public interface CartoesBloqueios {

	@RequestMapping(method = RequestMethod.POST, value = "/api/cartoes/{id}/bloqueios", consumes = "application/json")
	public ResultadoStatusCartao notificaSistemaLegado(@PathVariable String id, @RequestBody BloqueioStatusRequest sistemaResponsavel);
}
