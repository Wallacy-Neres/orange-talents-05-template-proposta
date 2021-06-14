package br.com.zup.proposta.biometria;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.proposta.cartao.Cartao;

@Entity
public class Biometria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String fingerprint;
	
	@NotNull
	private LocalDateTime dataCriacaoBiometria;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "cartao_id", nullable = false)
	private Cartao cartao;
	
	@Deprecated
	public Biometria() {
		
	}
	public Biometria(@NotBlank String biometria) {
		super();
		this.fingerprint = biometria;
		this.dataCriacaoBiometria = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}
	
	
	public String getFingerprint() {
		return fingerprint;
	}
	public void associaCartao(Cartao cartao) {
		this.cartao = cartao;
	}
}
