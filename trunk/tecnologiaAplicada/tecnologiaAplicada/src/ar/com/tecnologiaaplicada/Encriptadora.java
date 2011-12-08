package ar.com.tecnologiaaplicada;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Encriptadora {

	String algoritmo;// DES, TripleDES, AES ......
	SecretKey clave;

	public SecretKey getKey() {
		return clave;
	}

	public Encriptadora(String algoritmo, SecretKey clave) {
		this.algoritmo = algoritmo;
		this.clave = clave;
	}

	public Encriptadora(String algoritmo) {
		this.algoritmo = algoritmo;
		KeyGenerator kg;
		try {
			kg = KeyGenerator.getInstance(algoritmo);
			this.clave = kg.generateKey();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 

	}

	public byte[] encriptar(byte[] cadenaByte) {

		try {
			// ----------- Pasamos a Bytes para hacer bien el cifrado
			Cipher cifrar;

			cifrar = Cipher.getInstance(algoritmo);
			cifrar.init(Cipher.ENCRYPT_MODE, clave);
			byte[] enc = cifrar.doFinal(cadenaByte); // ciframos
			// ---------- Pasamos aBase64 para obtener un String
			return enc;
			

		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}

	public byte[] desencriptar(byte[] dec) {
		try {
			// ------------ Obtenemos los Bytes del String encriptado
			Cipher descifrar = Cipher.getInstance(algoritmo);
			descifrar.init(Cipher.DECRYPT_MODE, clave);
			byte[] cadenaByte = descifrar.doFinal(dec);
			
			return cadenaByte;
			// ------------- Volvemos a pasarlo a forma de cadena.
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	
	public String encriptar(String str) {
		byte[] bytes=encriptar(str.getBytes());
		
		return new sun.misc.BASE64Encoder().encode(bytes);
	}

	public String desencriptar(String str) {
		try {
			byte[] bytes=desencriptar(new sun.misc.BASE64Decoder().decodeBuffer(str));
			
			return new String(bytes, "UTF8");
		}catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
