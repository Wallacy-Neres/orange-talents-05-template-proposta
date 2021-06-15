package br.com.zup.proposta.bloqueio;

public class BloqueioStatusRequest {
	
	private String sistemaResponsavel;

	public BloqueioStatusRequest(String sistemaResponsavel) {
		super();
		this.sistemaResponsavel = sistemaResponsavel;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}
	
}
