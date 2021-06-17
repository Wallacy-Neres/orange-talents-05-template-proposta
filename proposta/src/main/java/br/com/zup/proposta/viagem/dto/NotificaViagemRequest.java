package br.com.zup.proposta.viagem.dto;

import java.time.LocalDate;

public class NotificaViagemRequest {

	private String destino;
	private LocalDate validoAte;
	
	@Deprecated
	public NotificaViagemRequest() {
		
	}
	
	public NotificaViagemRequest(String destino, LocalDate validoAte) {
		super();
		this.destino = destino;
		this.validoAte = validoAte;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getValidoAte() {
		return validoAte;
	}
	
	
}
