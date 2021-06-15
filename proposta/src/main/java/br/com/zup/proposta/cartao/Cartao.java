package br.com.zup.proposta.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.proposta.proposta.Proposta;

@Entity
public class Cartao {
	
	@Id
	private String id;
	
	@NotNull
	private LocalDateTime emitidoEm;
	
	@NotNull
	private String titular;
	
	@NotNull
	private BigDecimal limite;
	
	@NotNull
	@OneToOne
	private Proposta proposta;
	
	@Enumerated(EnumType.STRING)
	private StatusCartao statusCartao = StatusCartao.NAO_BLOQUEADO;
	
	@Deprecated
	public Cartao() {
		
	}


	public Cartao(@NotBlank String id, @NotNull LocalDateTime emitidoEm, @NotNull String titular,
			@NotNull BigDecimal limite, @NotNull Proposta proposta) {
		super();
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.proposta = proposta;
	}

	public String getId() {
		return id;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public Proposta getProposta() {
		return proposta;
	}
	
	
	
	public StatusCartao getStatusCartao() {
		return statusCartao;
	}


	public void setStatusCartao(StatusCartao statusCartao) {
		this.statusCartao = statusCartao;
	}


	public boolean cartaoBloqueado() {
		return this.statusCartao.equals(StatusCartao.BLOQUEADO);
	}
}
