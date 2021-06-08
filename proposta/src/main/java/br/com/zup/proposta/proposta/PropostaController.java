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

import br.com.zup.proposta.analise.SolicitacaoAnalise;
import br.com.zup.proposta.analise.analiseDTO.AnalisePropostaRequest;
import br.com.zup.proposta.analise.analiseDTO.ResultadoAnalise;
import br.com.zup.proposta.proposta.propostaDTO.EstadoProposta;
import br.com.zup.proposta.proposta.propostaDTO.PropostaRequest;
import feign.FeignException;

@RestController
@RequestMapping("proposta")
public class PropostaController {
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private SolicitacaoAnalise analise;
	
	@PostMapping
	public ResponseEntity<?> criarProposta(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder builder){
		Proposta proposta = request.converte();
		Optional<Proposta> documentoBanco = propostaRepository.findByDocumento(proposta.getDocumento());
		
		if(documentoBanco.isEmpty()) {
			propostaRepository.save(proposta);
			URI uri = builder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
			
			try {
				AnalisePropostaRequest analiseRequest = analiseProposta(proposta);
				ResultadoAnalise solicitacao = analise.solicitacao(analiseRequest);
				solicitacao.getResultadoSolicitacao().equals("SEM_RESTRICAO");
				proposta.setEstadoProposta(EstadoProposta.ELEGIVEL);
				propostaRepository.save(proposta);
			} catch (FeignException e) {
				e.printStackTrace();
				proposta.setEstadoProposta(EstadoProposta.NAO_ELEGIVEL);
				propostaRepository.save(proposta);
			}
		
			return ResponseEntity.created(uri).build();
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
	}

	private AnalisePropostaRequest analiseProposta(Proposta proposta) {
		AnalisePropostaRequest analiseRequest = new AnalisePropostaRequest(proposta.getDocumento(), proposta.getNome(), proposta.getId().toString());
		return analiseRequest;
	}
	
}
