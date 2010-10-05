package actualizaciones;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

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
	public static void upload1(String ftpServer, String user, String password,
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

	public static void download(String ftpServer, String user, String password,
			String fileName, File destination) {
		try {
			System.out.println("Server: " + ftpServer);
			FTPClient f = new FTPClient();

			f.connect(ftpServer);
			f.login(user, password);
			f.setFileType(FTP.BINARY_FILE_TYPE);
			f.enterLocalPassiveMode();

			OutputStream output;
			output = new FileOutputStream(destination);

			if (!f.printWorkingDirectory().equals("/")) {
				f.retrieveFile(f.printWorkingDirectory() + fileName, output);
			} else {
				f.retrieveFile(fileName, output);
			}

			output.close();
			System.out.println("Se descargó el archivo ftp: " + fileName
					+ " en " + destination.getAbsolutePath());

		} catch (SocketException e) {
			e.printStackTrace();
			throw new RuntimeException(e);

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
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
	public static void download1(String ftpServer, String user,
			String password, String fileName, File destination) {

		try {
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
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean existeConexionConServidor(String ftpServerUrl,
			String user, String password) {

		boolean existeConexion = false;

		FTPClient ftpClient = new FTPClient();

		try {
			ftpClient.connect(ftpServerUrl);
			existeConexion = ftpClient.login(user, password);
		} catch (Exception e) {
			System.out.println("No se pudo establecer conexión con el servidor " +ftpServerUrl);
			return false;
		} 
		return existeConexion;

	}

}// Fin de clase GestorFTP.
