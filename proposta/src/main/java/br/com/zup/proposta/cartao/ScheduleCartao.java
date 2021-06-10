package br.com.zup.proposta.cartao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zup.proposta.analise.AnalisePropostaRequest;
import br.com.zup.proposta.proposta.Proposta;
import br.com.zup.proposta.proposta.PropostaRepository;
import feign.FeignException;

@Component
public class ScheduleCartao {

	@Autowired
	private PropostaRepository propostaRepository;

	@Autowired
	private SolicitacaoCartao cartao;
	
	private Logger logger = LoggerFactory.getLogger(ScheduleCartao.class);

	@Scheduled(fixedDelayString = "${tempo.para.gerar.cartao}")
	public void buscaCartao() {
		List<Proposta> propostas = propostaRepository.FindByPropostasCartaoNull();
		
		try {
			
			propostas.forEach(proposta ->{
				RespostaCartao cartaoResponse = cartao.solicitaCartao(new AnalisePropostaRequest(proposta.getNome(), proposta.getDocumento(), proposta.getId().toString()));
				System.out.println(cartaoResponse.getTitular());
				Cartao cartao = cartaoResponse.converte(proposta);
				proposta.adicionaCartao(cartao);
				propostaRepository.save(proposta);
			});
		} catch (FeignException e) {
			logger.info("Erro no serviço responsavel por gerar cartão");
		}
	}
}
