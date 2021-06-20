package br.com.zup.proposta.proposta;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public class CriptografiaDeDocumentos {

	private static String hashDocumento = "@Neres*123";
	private static String salt = "5c0744940b5c369b";
	
	@SuppressWarnings("deprecation")
	private static TextEncryptor encriptar = Encryptors.queryableText(hashDocumento, salt);
	
	public static String criptografar(String documento) {
		return encriptar.encrypt(documento);
	}
	public static String descriptografar(String documento) {
		return encriptar.decrypt(documento);
	}
	
}
