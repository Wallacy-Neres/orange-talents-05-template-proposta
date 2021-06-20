package br.com.zup.proposta.proposta;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.proposta.propostaDTO.StatusProposta;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String documento;

	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String nome;

	@NotBlank
	private String endereco;

	@Enumerated(EnumType.STRING)
	private StatusProposta estadoProposta;

	@Positive
	@NotNull
	private BigDecimal salario;

	@OneToOne(cascade = CascadeType.MERGE)
	private Cartao cartao;

	@Deprecated
	public Proposta() {

	}

	public Proposta(@NotBlank String documento, @Email @NotBlank String email, @NotBlank String nome,
			@NotBlank String endereco, @Positive @NotNull BigDecimal salario) {
		super();
		this.documento = CriptografiaDeDocumentos.criptografar(documento);
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public StatusProposta getEstadoProposta() {
		return estadoProposta;
	}

	public void setEstadoProposta(StatusProposta estadoProposta) {
		this.estadoProposta = estadoProposta;
	}

	public void adicionaCartao(Cartao cartao) {
		this.cartao = cartao;

	}

}
