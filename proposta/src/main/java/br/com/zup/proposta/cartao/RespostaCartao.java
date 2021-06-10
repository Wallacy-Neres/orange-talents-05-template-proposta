package br.com.zup.proposta.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.proposta.proposta.Proposta;

public class RespostaCartao {
	
	
	@NotBlank
	private String id;
	
	@NotNull
	private LocalDateTime emitidoEm;
	
	@NotNull
	private String titular;
	
	@NotNull
	private BigDecimal limite;
	
	@NotNull
	private String idProposta;

	
	
	public RespostaCartao(@NotBlank String id, @NotNull LocalDateTime emitidoEm, @NotNull String titular,
			@NotNull BigDecimal limite, @NotNull String idProposta) {
		super();
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.idProposta = idProposta;
	}

	public Cartao converte(@Valid Proposta proposta) {
		if(proposta.getId() != Long.parseLong(idProposta)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe proposta com este ID");
		}
		return new Cartao(id, emitidoEm, titular, limite, proposta);
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

	public String getIdProposta() {
		return idProposta;
	}
	
	
	
}
