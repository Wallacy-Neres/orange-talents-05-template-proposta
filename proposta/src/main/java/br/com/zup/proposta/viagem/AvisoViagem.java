package br.com.zup.proposta.viagem;

import java.time.LocalDate;
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
public class AvisoViagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String destinoViagem;
	
	@NotNull
	private LocalDate dataTerminoViagem;

	@NotNull
	private LocalDateTime dataAvisoViagem;
	
	@NotBlank
	private String ipCliente;
	
	@NotBlank
	private String userAgent;
	
	@ManyToOne
	@JoinColumn(name = "cartao_aviso_id")
	private Cartao cartao;
	
	@Deprecated
	public AvisoViagem() {
		
	}

	public AvisoViagem(@NotBlank String destinoViagem, @NotNull LocalDate dataTerminoViagem, @NotBlank String ipCliente, @NotBlank String userAgent,
			Cartao cartao) {
		super();
		this.destinoViagem = destinoViagem;
		this.dataTerminoViagem = dataTerminoViagem;
		this.dataAvisoViagem = LocalDateTime.now();
		this.ipCliente = ipCliente;
		this.userAgent = userAgent;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	
}
