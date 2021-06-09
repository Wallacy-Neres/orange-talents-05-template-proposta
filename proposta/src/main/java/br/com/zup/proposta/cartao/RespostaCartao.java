package br.com.zup.proposta.cartao;

public class RespostaCartao {
	
	private String id;

	@Deprecated
	public RespostaCartao() {
		
	}
	
	public RespostaCartao(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
}
