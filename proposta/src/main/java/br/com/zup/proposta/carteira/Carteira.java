package br.com.zup.proposta.carteira;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.zup.proposta.cartao.Cartao;

@Entity
public class Carteira {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Email
	private String email;
	
	@Enumerated(EnumType.STRING)
	private CarteiraNome carteiraNome;
	
	@ManyToOne
	@JoinColumn(name = "Carteira_id_Cartao")
	private Cartao cartao;
	
	@Deprecated
	public Carteira() {
		
	}

	public Carteira(CarteiraNome carteiraNome, String email, Cartao cartao) {
		super();
		this.carteiraNome = carteiraNome;
		this.email = email;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}
	
	
}
