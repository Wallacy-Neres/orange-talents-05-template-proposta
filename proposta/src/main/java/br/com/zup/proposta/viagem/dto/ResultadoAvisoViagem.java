package br.com.zup.proposta.viagem.dto;

public class ResultadoAvisoViagem {

	private String resultado;

	@Deprecated
	public ResultadoAvisoViagem() {
		
	}
	
	public ResultadoAvisoViagem(String resultado) {
		super();
		this.resultado = resultado;
	}

	public String getResultado() {
		return resultado;
	}
	
}
