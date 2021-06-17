package br.com.zup.proposta.carteira.dto;

public class CarteiraAssociaRequest {
	
	private String email;
	private String carteira;
	
	@Deprecated
	public CarteiraAssociaRequest() {
		
	}
	
	public CarteiraAssociaRequest(String email, String carteira) {
		super();
		this.email = email;
		this.carteira = carteira;
	}


	public String getEmail() {
		return email;
	}


	public String getCarteira() {
		return carteira;
	}
	
	
}
