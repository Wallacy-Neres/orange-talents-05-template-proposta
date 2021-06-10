package br.com.zup.proposta.proposta;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.proposta.proposta.propostaDTO.PropostaResponse;

@RestController
@RequestMapping("proposta")
public class ConsultaPropostaController {

	@Autowired
	private PropostaRepository repository;

	@GetMapping("/{id}")
	public ResponseEntity<PropostaResponse> consultaProposta(@PathVariable Long id) {
		Optional<Proposta> consultaProposta = repository.findById(id);

		if (consultaProposta.isPresent()) {
			PropostaResponse proposta = new PropostaResponse(consultaProposta.get());
			return ResponseEntity.ok().body(proposta);
		}
		return ResponseEntity.notFound().build();
	}
}
