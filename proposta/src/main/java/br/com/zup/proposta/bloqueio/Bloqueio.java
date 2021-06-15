package br.com.zup.proposta.bloqueio;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.proposta.cartao.Cartao;

@Entity
public class Bloqueio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private LocalDateTime dataBloqueio;
	
	@NotBlank
	private String ipCliente;
	
	@NotBlank
	private String userAgent;

	@ManyToOne
	@JoinColumn(name = "idCartao")
	private Cartao cartao;

	public Bloqueio(@NotBlank String ipCliente, @NotBlank String userAgent) {
		super();
		this.ipCliente = ipCliente;
		this.dataBloqueio = LocalDateTime.now();
		this.userAgent = userAgent;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void insereCartao(Cartao cartaoASerBloqueado) {
		this.cartao = cartaoASerBloqueado;
	}
}
