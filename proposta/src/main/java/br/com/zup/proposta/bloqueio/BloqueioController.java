package br.com.zup.proposta.bloqueio;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.CartaoRepository;
import br.com.zup.proposta.cartao.StatusCartao;

@RestController
@RequestMapping("bloqueio")
public class BloqueioController {

	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private BloqueioRepository bloqueioRepository;
	
	@PostMapping("/{cartaoBloqueio}")
	public ResponseEntity<?> bloqueiaCartao(HttpServletRequest request, @RequestHeader(value = "User-Agent") String userAgent,@PathVariable String cartaoBloqueio){
		System.out.println(userAgent);
		
		Cartao cartaoASerBloqueado = cartaoRepository.findById(cartaoBloqueio);
		if(cartaoASerBloqueado == null) {
			return ResponseEntity.notFound().build();
		}
		
		if(cartaoASerBloqueado.cartaoBloqueado()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		cartaoASerBloqueado.setStatusCartao(StatusCartao.BLOQUEADO);
		BloqueioRequest requestBloqueio = new BloqueioRequest(pegaIpCliente(request), userAgent);
		Bloqueio bloqueio = requestBloqueio.converter();
		bloqueio.insereCartao(cartaoASerBloqueado);
		bloqueioRepository.save(bloqueio);
		return ResponseEntity.ok().build();
	}

	private String pegaIpCliente(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-FORWARDED-FOR"); 
			ipAddress = request.getRemoteAddr();
			return ipAddress;
	}
}
