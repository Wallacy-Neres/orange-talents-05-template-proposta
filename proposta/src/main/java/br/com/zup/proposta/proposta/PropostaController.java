package br.com.zup.proposta.proposta;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import br.com.zup.proposta.proposta.propostaDTO.PropostaRequest;

@RestController
@RequestMapping("proposta")
public class PropostaController {
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@PostMapping
	public ResponseEntity<?> criarProposta(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder builder){
		Proposta proposta = request.converte();
		Optional<Proposta> documentoBanco = propostaRepository.findByDocumento(proposta.getDocumento());
		if(documentoBanco.isEmpty()) {
			propostaRepository.save(proposta);
			URI uri = builder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
	}
}
