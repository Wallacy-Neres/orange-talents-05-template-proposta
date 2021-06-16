package br.com.zup.proposta.bloqueio;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.CartaoRepository;
import br.com.zup.proposta.cartao.StatusCartao;
import br.com.zup.proposta.feign.CartoesBloqueios;
import feign.FeignException.FeignClientException;

@RestController
@RequestMapping("bloqueio")
public class BloqueioController {

	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private BloqueioRepository bloqueioRepository;
	
	@Autowired
	private CartoesBloqueios cartoesBloqueio;
	
	@PostMapping("/{cartaoBloqueio}")
	public ResponseEntity<?> bloqueiaCartao(HttpServletRequest request, @RequestHeader(value = "User-Agent") String userAgent,@PathVariable String cartaoBloqueio){
		
		Cartao cartaoASerBloqueado = cartaoRepository.findById(cartaoBloqueio);
		if(cartaoASerBloqueado == null) {
			return ResponseEntity.notFound().build();
		}
		
		if(cartaoASerBloqueado.cartaoBloqueado()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		BloqueioRequest requestBloqueio = new BloqueioRequest(pegaIpCliente(request), userAgent);
		Bloqueio bloqueio = requestBloqueio.converter();
		
		try {
			bloqueio.insereCartao(cartaoASerBloqueado);
			cartoesBloqueio.notificaSistemaLegado(cartaoASerBloqueado.getId(), new BloqueioStatusRequest("proposta"));
			cartaoASerBloqueado.setStatusCartao(StatusCartao.BLOQUEADO);
			bloqueioRepository.save(bloqueio);
			return ResponseEntity.ok().build();
			
		} catch (FeignClientException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
	}

	private String pegaIpCliente(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-FORWARDED-FOR"); 
			ipAddress = request.getRemoteAddr();
			return ipAddress;
	}
}
