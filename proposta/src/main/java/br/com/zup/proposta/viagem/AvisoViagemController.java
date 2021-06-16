package br.com.zup.proposta.viagem;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.CartaoRepository;
import br.com.zup.proposta.viagem.dto.AvisoViagemRequest;

@RestController
@RequestMapping("viagem")
public class AvisoViagemController {
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private AvisoViagemRepository viagemRepository;
	
	@PostMapping("/{cartao}")
	public ResponseEntity<?> criarAvisoViagem(HttpServletRequest request, @PathVariable String cartao,  @RequestBody  @Valid AvisoViagemRequest viagemRequestDTO){
		Cartao cartaoAvisoViagem = cartaoRepository.findById(cartao);
		
		if(cartaoAvisoViagem == null) {
			return ResponseEntity.notFound().build();
		}
		String ipClient = pegaIpCliente(request);
		String userAgent = request.getHeader("User-Agent");
		AvisoViagem avisoViagem = viagemRequestDTO.converte(cartaoAvisoViagem, ipClient, userAgent);
		viagemRepository.save(avisoViagem);
		
		return ResponseEntity.ok().build();
	}
	
	private String pegaIpCliente(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-FORWARDED-FOR"); 
			ipAddress = request.getRemoteAddr();
			return ipAddress;
	}
	
}
