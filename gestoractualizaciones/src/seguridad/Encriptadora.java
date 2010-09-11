package seguridad;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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

import actualizaciones.GestorActualizaciones;

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
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String encriptar(String str) {

		try {
			// ----------- Pasamos a Bytes para hacer bien el cifrado
			byte[] cadenaByte = str.getBytes("UTF8");
			Cipher cifrar;

			cifrar = Cipher.getInstance(algoritmo);
			cifrar.init(Cipher.ENCRYPT_MODE, clave);
			byte[] enc = cifrar.doFinal(cadenaByte); // ciframos
			// ---------- Pasamos aBase64 para obtener un String
			return new sun.misc.BASE64Encoder().encode(enc);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException ex) {
			ex.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String desencriptar(String str) {
		try {
			// ------------ Obtenemos los Bytes del String encriptado
			byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
			Cipher descifrar = Cipher.getInstance(algoritmo);
			descifrar.init(Cipher.DECRYPT_MODE, clave);
			byte[] cadenaByte = descifrar.doFinal(dec);
			// ------------- Volvemos a pasarlo a forma de cadena.
			return new String(cadenaByte, "UTF8");
		} catch (Exception e) {
			GestorActualizaciones.setException(e, "Error al desencriptar datos con información del servidor");
			e.printStackTrace();
		} 
		return null;
	}
}
