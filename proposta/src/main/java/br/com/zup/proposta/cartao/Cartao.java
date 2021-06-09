package br.com.zup.proposta.cartao;

import java.time.LocalDateTime;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class Cartao {
	
	@Id
	private String id;
	
	@NotNull
	private LocalDateTime emitidoEm;
	
	@NotNull
	private String titular;
	
	private int limite;
	

	
	
	
	
}
