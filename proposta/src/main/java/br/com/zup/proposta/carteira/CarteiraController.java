package br.com.zup.proposta.carteira;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.CartaoRepository;
import br.com.zup.proposta.carteira.dto.CarteiraAssociaRequest;
import br.com.zup.proposta.carteira.dto.CarteriaRequest;
import br.com.zup.proposta.feign.CarteiraSistemaCartoes;
import feign.FeignException;

@RestController
@RequestMapping("carteira")
public class CarteiraController {
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private CarteiraRepository carteiraRepository;
	
	@Autowired
	private CarteiraSistemaCartoes carteiraSistema;
	
	@PostMapping("{cartao}")
	public ResponseEntity<?> criaCarteira(@PathVariable String cartao, @RequestBody @Valid CarteriaRequest request, UriComponentsBuilder builder){
		Cartao cartaoAdionarCarteira = cartaoRepository.findById(cartao);
		
		if(cartaoAdionarCarteira == null) {
			return ResponseEntity.notFound().build();
		}
		
		try {
			
			carteiraSistema.associaCarteira(cartaoAdionarCarteira.getId(), new CarteiraAssociaRequest(request.getEmail(), request.getCarteiraNome().toString()));
			Carteira carteira = request.converter(cartaoAdionarCarteira);
			carteiraRepository.save(carteira);
			URI uri = builder.path("/carteira/{id}").buildAndExpand(carteira.getId()).toUri();
			return ResponseEntity.created(uri).build();
			
		} catch (FeignException e) {
			e.printStackTrace();
			return ResponseEntity.unprocessableEntity().body("Este cartão já está associado a está carteira");
		}
	}
}
