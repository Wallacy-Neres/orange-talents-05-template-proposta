package br.com.zup.proposta.carteira.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.carteira.Carteira;
import br.com.zup.proposta.carteira.CarteiraNome;

public class CarteriaRequest {
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private CarteiraNome carteiraNome;
	
	@NotBlank
	@Email
	private String email;
	
	@Deprecated
	public CarteriaRequest() {
		
	}

	public CarteriaRequest(CarteiraNome carteiraNome, String email) {
		super();
		this.carteiraNome = carteiraNome;
		this.email = email;
	}
	
	public Carteira converter(Cartao cartao) {
		return new Carteira(carteiraNome, email, cartao);
	}

	public CarteiraNome getCarteiraNome() {
		return carteiraNome;
	}

	public String getEmail() {
		return email;
	}
	
	
}
