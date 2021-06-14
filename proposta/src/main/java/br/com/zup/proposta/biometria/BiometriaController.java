package br.com.zup.proposta.biometria;

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

import br.com.zup.proposta.biometria.biometriaDTO.BiometriaRequest;
import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.CartaoRepository;

@RestController
@RequestMapping("biometria")
public class BiometriaController {
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private BiometriaRepository biometriaRepository;
	
	@PostMapping("/{cartaoBiometria}")
	public ResponseEntity<?> cadastraBiometria(@PathVariable String cartaoBiometria ,@RequestBody @Valid BiometriaRequest request, UriComponentsBuilder builder){
		Cartao cartaoBanco = cartaoRepository.findById(cartaoBiometria);
		
		System.out.println(cartaoBanco);
		
		if(cartaoBanco == null) {
			return ResponseEntity.notFound().build();
		}
		
		Biometria fingerprint = request.converte();
		fingerprint.associaCartao(cartaoBanco);
		biometriaRepository.save(fingerprint);
		URI uri = builder.path("/biometria/{id}").buildAndExpand(fingerprint.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
