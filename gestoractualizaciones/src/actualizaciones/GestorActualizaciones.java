package actualizaciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.security.MessageDigest;
import java.util.LinkedList;
import javax.crypto.spec.SecretKeySpec;

import seguridad.Encriptadora;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * 
 * @author Chipio
 * 
 */

public class GestorActualizaciones extends Thread {

	private static Exception exception;
	private static String mensajeExcepcionParaElUsuario;
	private static boolean seEncontroActualizacion = false;
	
	/*

	private static String ftpActualUrl;
	private static String ftpActualNombreUsuario;
	private static String ftpActualPassword;
	private static String ftpPrincipalUrl;
	private static String ftpPrincipalNombreUsuario;
	private static String ftpPrincipalPassword;
	private static String ftpSecundarioUrl;
	private static String ftpSecundarioNombreUsuario;
	private static String ftpSecundarioPassword;
	private static String codigoRegionDeDestino;
	private static String versionPrincipal;*/

	public static Exception getException() {
		return exception;
	}

	public static void setException(Exception exc,
			String MensajeExcepcionParaElUsuario) {
		if (exception ==null) {//Queremos ver el error de la primera excepción, no de las que puedan suceden a consecuencia de la primera.
			exception = exc;
			setMensajeExcepcionParaElUsuario(MensajeExcepcionParaElUsuario);	
		}
		
	}

	public static String getMensajeExcepcionParaElUsuario() {
		return mensajeExcepcionParaElUsuario;
	}

	public static void setMensajeExcepcionParaElUsuario(
			String mensajeExcepcionParaElUsuario) {
		GestorActualizaciones.mensajeExcepcionParaElUsuario = mensajeExcepcionParaElUsuario;
	}

	public static void setSeEncontroActualizacion(
			boolean seEncontroActualizacion) {
		GestorActualizaciones.seEncontroActualizacion = seEncontroActualizacion;
	}

	public static boolean getSeEncontroActualizacion() {
		return seEncontroActualizacion;
	}

	@Override
	public void run() {

		GestorActualizacionesUtil
				.cargarParametrosDeActualizacionDesdeArchivo(new File(System
						.getProperty("user.dir")));

		GestorActualizacionesUtil.establecerServidorAUtilizar();

		try {
			GestorActualizacionesUtil.ejecutarActualizacionDeArchivos(new File(System.getProperty("user.dir")));
		} catch (Exception e) {
			GestorActualizaciones.setException(e, "Error: Descargando los archivos.");
			e.printStackTrace();
		}

	}


	
	private static File obtenerArchivoConIndicesMD5DesdeServidorFTP(File directorioDestinoArchivo) {
		try {
			return GestorActualizacionesUtil.obtenerArchivoConIndicesMD5DesdeServidorFTP(directorioDestinoArchivo);
		} catch (Exception e) {
			GestorActualizaciones.setException(e, "Error: no se pudo conectar con el servidor FTP.");
			e.printStackTrace();
		} 
		return null;
	}

	/**
	 * Obtiene un archivo desde un servidor FTP.
	 * 
	 * @param nombreArchivoOrigen
	 *            . Nombre del archivo que se desea bajar del servidor FTP.
	 * @param directorioDestino
	 *            . Directorio donde se almacenará el archivo bajado.
	 *            @param rutaDelArchivoDentroDeRaizFTP.
	 *            . Ruta del archivo dentro de la raíz del programa en el servidor ftp.
	 */
	public static void obtenerArchivoDesdeServidorFTP(String nombreArchivoOrigen, String rutaDelArchivoDentroDeRaizEnFTP, File directorioDestino)
	{
		try {
			GestorActualizacionesUtil.obtenerArchivoDesdeServidorFTP(nombreArchivoOrigen, rutaDelArchivoDentroDeRaizEnFTP, directorioDestino);

		} catch (Exception e) {
			GestorActualizaciones.setException(e, "Error: no se pudo conectar con el servidor FTP.");
			e.printStackTrace();
		}// Archivo de destino
	}


}// Fin de clase GestorActualizaciones del Gestor de Actualizaciones
