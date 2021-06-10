package br.com.zup.proposta.proposta.propostaDTO;


import br.com.zup.proposta.proposta.Proposta;

public class PropostaResponse {
	
	private String documento;
	
	private String email;
	
	private String nome;
	
	private StatusProposta estadoProposta;

	public PropostaResponse(Proposta proposta) {
		this.documento = proposta.getDocumento();
		this.email = proposta.getEmail();
		this.nome = proposta.getNome();
		this.estadoProposta = proposta.getEstadoProposta();
	}
	
	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public StatusProposta getEstadoProposta() {
		return estadoProposta;
	}
	
	
}
