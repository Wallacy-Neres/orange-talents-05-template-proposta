package br.com.zup.proposta.proposta;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import br.com.zup.proposta.analise.AnalisePropostaRequest;
import br.com.zup.proposta.analise.ResultadoAnalise;
import br.com.zup.proposta.feign.SolicitacaoAnalise;
import br.com.zup.proposta.proposta.propostaDTO.StatusProposta;
import io.opentracing.Span;
import io.opentracing.Tracer;
import br.com.zup.proposta.proposta.propostaDTO.PropostaRequest;

@RestController
@RequestMapping("proposta")
public class CriarPropostaController {
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private SolicitacaoAnalise analise;
	
	@Autowired
	private Tracer tracer;
	
	@PostMapping
	public ResponseEntity<PropostaRequest> criarProposta(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder builder){
		Proposta proposta = request.converte();
		
		
				//COMECO TRACING TEST
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Span activeSpan = tracer.activeSpan();
		activeSpan.setTag("tag.teste", "trancing");
		activeSpan.setTag("usuario", auth.getName().toString());
		activeSpan.setBaggageItem("usuario", auth.getName().toString());
		activeSpan.log("Log Proposta:" + "Usuario: " +activeSpan.getBaggageItem("usuario"));
				//FIM TRACING
		
		Optional<Proposta> documentoBanco = propostaRepository.findByDocumento(proposta.getDocumento());
		
		if(documentoBanco.isEmpty()) {
			propostaRepository.save(proposta);
			URI uri = builder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
			
			try {
				AnalisePropostaRequest analiseRequest = analiseProposta(proposta);
				ResultadoAnalise solicitacao = analise.solicitacao(analiseRequest);
				solicitacao.getResultadoSolicitacao().equals("SEM_RESTRICAO");
				proposta.setEstadoProposta(StatusProposta.ELEGIVEL);
				
				propostaRepository.save(proposta);
			} catch (Exception e) {
				e.printStackTrace();
				proposta.setEstadoProposta(StatusProposta.NAO_ELEGIVEL);
				propostaRepository.save(proposta);
			}
		
			return ResponseEntity.created(uri).build();
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
	}

	private AnalisePropostaRequest analiseProposta(Proposta proposta) {
		AnalisePropostaRequest analiseRequest = new AnalisePropostaRequest(CriptografiaDeDocumentos.descriptografar(proposta.getDocumento()), proposta.getNome(), proposta.getId().toString());
		return analiseRequest;
	}
	
}
