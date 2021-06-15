package br.com.zup.proposta.bloqueio;

import javax.validation.constraints.NotBlank;

public class BloqueioRequest {
	
	
	@NotBlank
	private String ipCliente;
	
	@NotBlank
	private String userAgent;

	public BloqueioRequest(@NotBlank String ipCliente,@NotBlank String userAgent) {
		super();
		this.ipCliente = ipCliente;
		this.userAgent = userAgent;
	}

	public Bloqueio converter() {
		return new Bloqueio(ipCliente, userAgent);
	}
}
