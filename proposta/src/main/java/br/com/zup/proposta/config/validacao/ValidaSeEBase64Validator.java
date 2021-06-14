package br.com.zup.proposta.config.validacao;

import java.util.Base64;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.zup.proposta.biometria.biometriaDTO.BiometriaRequest;

public class ValidaSeEBase64Validator implements ConstraintValidator<ValidaSeEBase64Value, BiometriaRequest>{

	@Override
	public boolean isValid(BiometriaRequest fingerprint, ConstraintValidatorContext context) {
		try {
			 Base64.getDecoder().decode(fingerprint.getFingerprint());
			 return true;
		} catch (Exception e) {
			return false;
		}
	}
}
