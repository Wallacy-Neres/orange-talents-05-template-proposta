package br.com.zup.proposta.biometria.biometriaDTO;

import javax.validation.constraints.NotBlank;

import br.com.zup.proposta.biometria.Biometria;
import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.config.validacao.ValidaSeEBase64Value;

@ValidaSeEBase64Value
public class BiometriaRequest {
	
	
	@NotBlank
	private String fingerprint;
	
	@Deprecated
	public BiometriaRequest() {
		
	}
	
	public BiometriaRequest(@NotBlank String fingerprint) {
		super();
		this.fingerprint = fingerprint;
	}

	public Biometria converte() {
		return new Biometria(fingerprint);
	}

	public String getFingerprint() {
		return fingerprint;
	}
	
	
}
