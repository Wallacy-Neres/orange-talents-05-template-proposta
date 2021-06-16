package br.com.zup.proposta.viagem.dto;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.viagem.AvisoViagem;

public class AvisoViagemRequest {
	
	@NotBlank
	private String destinoViagem;
	
	@NotNull
	@Future @JsonFormat(pattern="dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate dataTerminoViagem;

	@Deprecated
	public AvisoViagemRequest() {
		
	}

	public AvisoViagemRequest(@NotBlank String destinoViagem, @NotNull LocalDate dataTerminoViagem) {
		super();
		this.destinoViagem = destinoViagem;
		this.dataTerminoViagem = dataTerminoViagem;
	}

	public AvisoViagem converte(Cartao cartao, String IpClient, String userAgent) {
		return new AvisoViagem(destinoViagem, dataTerminoViagem, IpClient, userAgent, cartao);
	}

	public String getDestinoViagem() {
		return destinoViagem;
	}

	public LocalDate getDataTerminoViagem() {
		return dataTerminoViagem;
	}
	
	
}
