package actualizaciones;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Esta clase se usa para subir y bajar archivos a un servidor FTP.
 * 
 * @author Muthu
 */

public class GestorFTP {

	/**
	 * Descarga un archivo desde un servidor FTP. Se genera una URL FTP con la
	 * siguiente sintaxis: ftp://user:password@host:port/filePath;type=i.
	 * 
	 * @param ftpServer
	 *            , dirección del servidor FTP (especificar puerto es opcional
	 *            ':portNumber').
	 * @param user
	 *            , nombre de usuario para hacer el login (opcional).
	 * @param password
	 *            , password del usuario (opcional).
	 * @param fileName
	 *            , nombre de archivo de destino en el servidor FTP
	 *            (opcionalmente puede estar precedido por su ruta relativa, por
	 *            ej. imagenes/close.png).
	 * @param source
	 *            , Archiivo de origen a subir.
	 * @throws MalformedURLException
	 *             , IOException.
	 */
	public static void upload(String ftpServer, String user, String password,
			String fileName, File source) throws MalformedURLException,
			IOException {
		if (ftpServer != null && fileName != null && source != null) {
			StringBuffer sb = new StringBuffer("ftp://");
			// Verifica si existen datos de autentificación, si no existen asume
			// que es un acceso anónimo.
			if (user != null && password != null) {
				sb.append(user);
				sb.append(':');
				sb.append(password);
				sb.append('@');
			}
			sb.append(ftpServer);
			sb.append('/');
			sb.append(fileName);
			/*
			 * Modos de listado: type ==> a=ASCII mode, i=image (binary) mode,
			 * d= file directory
			 */
			sb.append(";type=i");

			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				URL url = new URL(sb.toString());
				URLConnection urlc = url.openConnection();

				bos = new BufferedOutputStream(urlc.getOutputStream());
				bis = new BufferedInputStream(new FileInputStream(source));

				int i;
				// lee byte por byte hasta el fin del Stream
				while ((i = bis.read()) != -1) {
					bos.write(i);
				}
			} finally {
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}
				if (bos != null) {
					try {
						bos.close();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}
			}
		} else {
			System.out.println("No se pudo realizar la transacción.");
		}
	}

	/**
	 * Descarga un archivo desde un servidor FTP. Se genera una URL FTP con la
	 * siguiente sintaxis: ftp://user:password@host:port/filePath;type=i.
	 * 
	 * @param ftpServer
	 *            , dirección del servidor FTP (especificar puerto es opcional
	 *            ':portNumber').
	 * @param user
	 *            , nombre de usuario para hacer el login (opcional).
	 * @param password
	 *            , password del usuario (opcional).
	 * @param fileName
	 *            , nombre del archivo a descargar (opcionalmente puede estar
	 *            precedido por su ruta relativa, por ej. imagenes/close.png).
	 * @param destination
	 *            , Archivo de destino.
	 * @throws MalformedURLException
	 *             , IOException.
	 */
	public static void download(String ftpServer, String user, String password,
			String fileName, File destination){

		try
		{
			StringBuffer sb = new StringBuffer("ftp://");
			// Verifica si existen datos de autentificación, si no existen asume
			// que es un acceso anónimo.
			if (user != null && password != null) {
				sb.append(user);
				sb.append(':');
				sb.append(password);
				sb.append('@');
			}
			sb.append(ftpServer);
			sb.append('/');
			sb.append(fileName);
			/*
			 * Modos de listado: type ==> a=ASCII mode, i=image (binary) mode,
			 * d= file directory
			 */
			sb.append(";type=i");
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				URL url = new URL(sb.toString());
				URLConnection urlc = url.openConnection();

				bis = new BufferedInputStream(urlc.getInputStream());
				bos = new BufferedOutputStream(new FileOutputStream(destination
						.getAbsolutePath()));

				int i;
				while ((i = bis.read()) != -1) {
					bos.write(i);
				}
			} finally {
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}
				if (bos != null) {
					try {
						bos.close();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}
			}
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public static boolean existeConexionConServidor(String ftpServerUrl,
			String user, String password) {

		if (ftpServerUrl != null) {
			StringBuffer sb = new StringBuffer("ftp://");
			// Verifica si existen datos de autentificación, si no existen asume
			// que es un acceso anónimo.
			if (user != null && password != null) {
				sb.append(user);
				sb.append(':');
				sb.append(password);
				sb.append('@');
			}
			sb.append(ftpServerUrl);
			sb.append('/');

			try {
				URL url = new URL(sb.toString());
				URLConnection urlc = url.openConnection();
				urlc.connect();
				return true;// Se pudo conectar con el servidor FTP.
			}

			catch (IOException ex) {
				return false;// No se pudo conectar con el servidor FTP.
			}

		} else {
			return false;
		}
	}

}// Fin de clase GestorFTP.
