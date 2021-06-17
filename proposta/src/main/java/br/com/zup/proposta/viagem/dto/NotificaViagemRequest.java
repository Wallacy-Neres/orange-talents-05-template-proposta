package br.com.zup.proposta.viagem.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NotificaViagemRequest {

	@NotBlank
	private String destino;
	
	@NotNull
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
