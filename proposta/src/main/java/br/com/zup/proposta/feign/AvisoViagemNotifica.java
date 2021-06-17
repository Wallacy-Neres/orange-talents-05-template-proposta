package br.com.zup.proposta.feign;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import br.com.zup.proposta.viagem.dto.NotificaViagemRequest;
import br.com.zup.proposta.viagem.dto.ResultadoAvisoViagem;

@FeignClient(name = "AvisoViagem", url="${servico.recurso.cartao}")
public interface AvisoViagemNotifica {

	@RequestMapping(method = RequestMethod.POST, value = "/api/cartoes/{id}/avisos", consumes = "application/json")
	public ResultadoAvisoViagem notificaViagem(@PathVariable String id, @RequestBody @Valid NotificaViagemRequest notifica);
}
