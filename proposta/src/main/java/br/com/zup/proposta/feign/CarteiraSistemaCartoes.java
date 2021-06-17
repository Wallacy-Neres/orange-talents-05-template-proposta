package br.com.zup.proposta.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.zup.proposta.carteira.dto.CarteiraAssociaRequest;
import br.com.zup.proposta.carteira.dto.ResultadoCarteira;

@FeignClient(name = "AssociaCarteira", url="${servico.recurso.cartao}")
public interface CarteiraSistemaCartoes {
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/cartoes/{id}/carteiras", consumes = "application/json")
	public ResultadoCarteira associaCarteira(@PathVariable String id, @RequestBody CarteiraAssociaRequest request);
}

