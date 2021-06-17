package br.com.zup.proposta.viagem;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.CartaoRepository;
import br.com.zup.proposta.feign.AvisoViagemNotifica;
import br.com.zup.proposta.viagem.dto.AvisoViagemRequest;
import br.com.zup.proposta.viagem.dto.NotificaViagemRequest;
import br.com.zup.proposta.viagem.dto.ResultadoAvisoViagem;
import feign.FeignException;

@RestController
@RequestMapping("viagem")
public class AvisoViagemController {
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private AvisoViagemRepository viagemRepository;
	
	@Autowired
	private AvisoViagemNotifica avisoViagemNotifica;
	
	@PostMapping("/{cartao}")
	public ResponseEntity<?> criarAvisoViagem(HttpServletRequest request, @PathVariable String cartao,  @RequestBody  @Valid AvisoViagemRequest viagemRequestDTO){
		Cartao cartaoAvisoViagem = cartaoRepository.findById(cartao);
		
		if(cartaoAvisoViagem == null) {
			return ResponseEntity.notFound().build();
		}
	
		try {
			String ipClient = pegaIpCliente(request);
			String userAgent = request.getHeader("User-Agent");
			ResultadoAvisoViagem notificaViagem = avisoViagemNotifica.notificaViagem(cartaoAvisoViagem.getId(), new NotificaViagemRequest(viagemRequestDTO.getDestinoViagem(), viagemRequestDTO.getDataTerminoViagem()));
			System.out.println(notificaViagem.getResultado());
			AvisoViagem avisoViagem = viagemRequestDTO.converte(cartaoAvisoViagem, ipClient, userAgent);
			viagemRepository.save(avisoViagem);
			
			return ResponseEntity.ok().build();
		} catch (FeignException e) {
			e.printStackTrace();
			return ResponseEntity.unprocessableEntity().body("Não foi possivel realizar o aviso viagem");
		}
			
		
	}
	
	private String pegaIpCliente(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-FORWARDED-FOR"); 
			ipAddress = request.getRemoteAddr();
			return ipAddress;
	}
	
}
