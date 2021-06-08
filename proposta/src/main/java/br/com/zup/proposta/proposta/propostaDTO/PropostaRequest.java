package br.com.zup.proposta.proposta.propostaDTO;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.proposta.config.validacao.ValidaCPFouCNPJValue.CPFOrCNPJ;
import br.com.zup.proposta.proposta.Proposta;

public class PropostaRequest {
	
	@CPFOrCNPJ
	@NotBlank
	private String documento;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String endereco;
	
	@Positive
	@NotNull
	private BigDecimal salario;
	
	public PropostaRequest(@NotBlank String documento, @NotBlank String email, @NotBlank String nome,
			@NotBlank String endereco, @Positive @NotNull BigDecimal salario) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Proposta converte() {
		return new Proposta(documento, email, nome, endereco, salario);
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}	
	
	
}
