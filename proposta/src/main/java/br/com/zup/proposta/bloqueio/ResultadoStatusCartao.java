package br.com.zup.proposta.bloqueio;

public class ResultadoStatusCartao {

	private String resultado;
	
	@Deprecated
	public ResultadoStatusCartao() {
		
	}

	public ResultadoStatusCartao(String resultado) {
		super();
		this.resultado = resultado;
	}

	public String getResultado() {
		return resultado;
	}
}
