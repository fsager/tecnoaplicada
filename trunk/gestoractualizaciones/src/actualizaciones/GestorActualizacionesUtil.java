package actualizaciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import seguridad.Encriptadora;

public class GestorActualizacionesUtil {

	private final static String stringClavePrivada = new String(
			"czbmrndoritlekaz");
	private final static String algoritmoEncriptacionClavePrivada = new String(
			"AES");

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
	private static String versionPrincipal;
	private static String nombreAplicacion;
	private static String nombreArchivoActualDescargando = new String();
	private static int cantidadArchivosDescargando = 0;
	private static int cantidadTotalArchivos = 0;

	public static void init(String ftpPrincipalUrl,
			String ftpPrincipalNombreUsuario, String ftpPrincipalPassword,
			String ftpSecundarioUrl, String ftpSecundarioNombreUsuario,
			String ftpSecundarioPassword, String codigoRegionDeDestino,
			String versionPrincipal, String nombreAplicacion) {
		GestorActualizacionesUtil.ftpPrincipalUrl = ftpPrincipalUrl;
		GestorActualizacionesUtil.ftpPrincipalNombreUsuario = ftpPrincipalNombreUsuario;
		GestorActualizacionesUtil.ftpPrincipalPassword = ftpPrincipalPassword;
		GestorActualizacionesUtil.ftpSecundarioUrl = ftpSecundarioUrl;
		GestorActualizacionesUtil.ftpSecundarioNombreUsuario = ftpSecundarioNombreUsuario;
		GestorActualizacionesUtil.ftpSecundarioPassword = ftpSecundarioPassword;
		GestorActualizacionesUtil.codigoRegionDeDestino = codigoRegionDeDestino;
		GestorActualizacionesUtil.versionPrincipal = versionPrincipal;
		GestorActualizacionesUtil.nombreAplicacion = nombreAplicacion;
		establecerServidorAUtilizar();
	}

	public static String getFtpActualNombreUsuario() {
		String ftpActualNombreUsuarioDesencriptado = desencriptarString(ftpActualNombreUsuario);
		return ftpActualNombreUsuarioDesencriptado;
	}

	public static String getFtpActualPassword() {
		String ftpActualPasswordDesencriptado = desencriptarString(ftpActualPassword);
		return ftpActualPasswordDesencriptado;
	}

	public static String getFtpPrincipalUrl() {
		String ftpPrincipalUrlDesencriptada = desencriptarString(ftpPrincipalUrl);
		return ftpPrincipalUrlDesencriptada;
	}

	public static String getFtpPrincipalNombreUsuario() {
		String ftpPrincipalNombreUsuarioDesencriptado = desencriptarString(ftpPrincipalNombreUsuario);
		return ftpPrincipalNombreUsuarioDesencriptado;
	}

	public static String getFtpPrincipalPassword() {
		String ftpPrincipalPasswordDesencriptado = desencriptarString(ftpPrincipalPassword);
		return ftpPrincipalPasswordDesencriptado;
	}

	public static String getFtpSecundarioUrl() {
		String ftpSecundarioUrlDesencriptada = desencriptarString(ftpSecundarioUrl);
		return ftpSecundarioUrlDesencriptada;
	}

	public static String getFtpSecundarioNombreUsuario() {
		String ftpSecundarioNombreUsuarioDesencriptado = desencriptarString(ftpSecundarioNombreUsuario);
		return ftpSecundarioNombreUsuarioDesencriptado;
	}

	public static String getFtpSecundarioPassword() {
		String ftpSecundarioPasswordDesencriptado = desencriptarString(ftpSecundarioPassword);
		return ftpSecundarioPasswordDesencriptado;
	}

	public static String getFtpActualUrl() {
		String ftpActualUrlDesencriptada = desencriptarString(ftpActualUrl);
		return ftpActualUrlDesencriptada;
	}

	public static String getCodigoRegionDeDestino() {
		return codigoRegionDeDestino;
	}

	public static String getVersionPrincipal() {
		return versionPrincipal;
	}

	public static String getNombreAplicacion() {
		return nombreAplicacion;
	}

	public static String getNombreArchivoActualDescargando() {
		return nombreArchivoActualDescargando;
	}

	public static int getCantidadArchivosDescargando() {
		return cantidadArchivosDescargando;
	}

	public static int getCantidadTotalArchivos() {
		return cantidadTotalArchivos;
	}

	/**
	 * Establece qué servidor FTP se utilizará para el proceso de actualización
	 * actual. En caso que el servidor principal no esté disponible, se
	 * utilizará el secundario.
	 * 
	 * @return URL del servidor utilizado...
	 */
	public static String[] establecerServidorAUtilizar() {
		try {
			if (GestorFTP.existeConexionConServidor(getFtpPrincipalUrl(),
					getFtpPrincipalNombreUsuario(), getFtpPrincipalPassword()) == true) {

				ftpActualUrl = ftpPrincipalUrl;
				ftpActualNombreUsuario = ftpPrincipalNombreUsuario;
				ftpActualPassword = ftpPrincipalPassword;
			} else if (GestorFTP.existeConexionConServidor(
					getFtpSecundarioUrl(), getFtpSecundarioNombreUsuario(),
					getFtpSecundarioPassword()) == true) {
				ftpActualUrl = ftpSecundarioUrl;
				ftpActualNombreUsuario = ftpSecundarioNombreUsuario;
				ftpActualPassword = ftpSecundarioPassword;
			} else {
				throw new Exception(
						"No se ha podido establecer la conexión con el servidor de actualizaciones primario, ni secundario.");
			}

			String[] datosRetorno = new String[2];

			datosRetorno[0] = getFtpActualUrl();
			datosRetorno[1] = getFtpActualNombreUsuario();

			return datosRetorno;

		} catch (Exception ex) {
			RuntimeException exRuntime = new RuntimeException(
					"No se ha podido establecer la conexión con el servidor de actualizaciones.",
					ex);
			throw exRuntime;
		}
	}

	/**
	 * 
	 * Comprueba si un archivo está actualizado o no con respecto de su versión
	 * en el servidor. Si el archivo no existe en la aplicación local, el método
	 * devuelve false y se baja el archivo del servidor.
	 * 
	 * @return true si el archivo está actualizado con respecto al del servidor.
	 *         False si el archivo está desactualizado.
	 */
	private static boolean estaActualizado(File directorioActual,
			String nombreArchivo, String md5) throws IOException {
		boolean estaActualizado = false;
		String rutaAbsolutaArchivoAVerificar;
		String strDirectorioActual = directorioActual.getAbsolutePath();
		nombreArchivo = nombreArchivo.replace('/', '\\');

		if (nombreArchivo.startsWith("\\")) {
			/*
			 * Es un archivo en subcarpeta
			 */
			rutaAbsolutaArchivoAVerificar = strDirectorioActual
					.concat(nombreArchivo);

		} else {
			/*
			 * Es un archivo en la carpeta raíz
			 */
			rutaAbsolutaArchivoAVerificar = strDirectorioActual
					.concat(File.separator + nombreArchivo);
		}

		File fileArchivoAVerificar = new File(rutaAbsolutaArchivoAVerificar);

		if (fileArchivoAVerificar.exists()) {
			/*
			 * El archivo local existe, veremos si está actualizado.
			 */
			if (md5.equals(getMD5(fileArchivoAVerificar))) {
				System.out.println("EL ARCHIVO ESTABA ACTUALIZADO: "
						+ fileArchivoAVerificar.getName());
				estaActualizado = true;

			} else {
				System.out.println("EL ARCHIVO NO ESTABA ACTUALIZADO: "
						+ fileArchivoAVerificar.getName());
				estaActualizado = false;
			}

		}
		return estaActualizado;

	}

	/**
	 * Calcula y retorna el código MD5 de un archivo.
	 * 
	 * @return código MD5 del archivo.
	 */
	public static String getMD5(File archivo) {

		try {
			String stringMD5 = new String();
			MessageDigest messageDigest = MessageDigest.getInstance("MD5"); // Inicializa
			// MD5
			InputStream isArchivo = new FileInputStream(archivo); // leer
			// fichero
			// byte a
			// byte

			byte[] buffer = new byte[1];
			int fin_archivo = -1;
			int caracter;

			caracter = isArchivo.read(buffer);

			while (caracter != fin_archivo) {

				messageDigest.update(buffer); // Pasa texto claro a la función
				// resumen
				caracter = isArchivo.read(buffer);
			}

			isArchivo.close();
			byte[] resumenMD5 = messageDigest.digest(); // Genera el resumen MD5

			// Pasar los resumenes a hexadecimal
			stringMD5 = "";
			for (int i = 0; i < resumenMD5.length; i++) {
				stringMD5 += Integer.toHexString((resumenMD5[i] >> 4) & 0xf);
				stringMD5 += Integer.toHexString(resumenMD5[i] & 0xf);
			}

			return stringMD5;
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener el archivo MD5", e);
		}
	}

	/**
	 * Genera un archivo que contiene un listado de archivos de una carpeta que
	 * se recibe como parámetro. El archivo de salida se guarda en la carpeta de
	 * origen.
	 * 
	 * @param File
	 *            que contiene el directorio donde se encuentran los archivos a
	 *            procesar.
	 */
	public static void generarArchivoConIndicesMD5(File carpetaArchivosOrigen) {

		try {
			File archivosEnCarpeta[] = carpetaArchivosOrigen.listFiles();
			File archivoMD5 = new File(carpetaArchivosOrigen + File.separator
					+ "files.md5");
			FileWriter fw = new FileWriter(archivoMD5);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);

			for (int i = 0; i < archivosEnCarpeta.length; i++) {
				salida.println(archivosEnCarpeta[i].getName() + ":"
						+ getMD5(archivosEnCarpeta[i]));
			}
			salida.close();
		} catch (IOException ex) {
		}

	}

	/**
	 * Obtiene un archivo desde la carpeta "common" del Servidor FTP. Esta
	 * carpeta contiene archivos comunes a todas las versiones del programa.
	 */
	public static void obtenerArchivoComunDeTodasLasVersiones(
			String nombreArchivoOrigen, File directorioDestino) {
		/*
		 * Si el directorio destino no existe, lo creamos junto con sus
		 * directorios padres.
		 */
		if (!directorioDestino.exists()) {
			directorioDestino.mkdirs();
		}

		try {
			GestorFTP.download(getFtpActualUrl(),// URL del FTP
					getFtpActualNombreUsuario(),// Nombre de usuario
					getFtpActualPassword(),// Password
					"/"+getNombreAplicacion()+"/Common/" + nombreArchivoOrigen,// Archivo de
					// origen en
					// servidor.
					new File(directorioDestino + File.separator
							+ nombreArchivoOrigen + ".ftptemp"));// Archivo de
			// destino
		} catch (Exception e) {
			GestorActualizaciones.setException(e,
					"Error: no se pudo conectar con el servidor FTP.");
			e.printStackTrace();
		}

	}

	/**
	 * Obtiene un archivo desde un servidor FTP.
	 * 
	 * @param nombreArchivoOrigen
	 *            . Nombre del archivo que se desea bajar del servidor FTP.
	 * @param directorioDestino
	 *            . Directorio donde se almacenará el archivo bajado.
	 */
	public static void obtenerArchivoDesdeServidorFTP(
			String nombreArchivoOrigen, String rutaDelArchivoDentroDeRaizEnFTP,
			File directorioDestino) {

		/*
		 * Si el directorio destino no existe, lo creamos junto con sus
		 * directorios padres.
		 */
		if (!directorioDestino.exists()) {
			directorioDestino.mkdirs();
		}

		if (rutaDelArchivoDentroDeRaizEnFTP == null) {
			rutaDelArchivoDentroDeRaizEnFTP = new String("");
		} else {
			if (!rutaDelArchivoDentroDeRaizEnFTP.isEmpty()) {
				rutaDelArchivoDentroDeRaizEnFTP = rutaDelArchivoDentroDeRaizEnFTP
						.replace('\\', '/');
			}

		}

		System.out.println("RUTA DENTRO DEL FTP: "
				+ rutaDelArchivoDentroDeRaizEnFTP);

		nombreArchivoActualDescargando = nombreArchivoOrigen;
		cantidadArchivosDescargando = cantidadArchivosDescargando + 1;
		
		try {
			GestorFTP.download(getFtpActualUrl(),// URL del FTP
					getFtpActualNombreUsuario(),// Nombre de usuario
					getFtpActualPassword(),// Password
					"/"+getNombreAplicacion()+"/" + getCodigoRegionDeDestino() + "/"
							+ getVersionPrincipal() + "/"
							+ rutaDelArchivoDentroDeRaizEnFTP
							+ nombreArchivoOrigen,// Archivo de
					// origen en
					// servidor.
					new File(directorioDestino + File.separator
							+ nombreArchivoOrigen + ".ftptemp"));// Archivo de
			// destino


		} catch (Exception e) {
			GestorActualizaciones.setException(e,
					"Error: no se pudo conectar con el servidor FTP.");
			e.printStackTrace();
		}
	}

	public static File obtenerArchivoConIndicesMD5DesdeServidorFTP(
			File directorioDestinoArchivo) {
		String nombreArchivoMD5DesdeServidor = new String("files.md5");
		File rutaArchivoMD5 = new File(directorioDestinoArchivo
				.getAbsolutePath()
				+ File.separator + nombreArchivoMD5DesdeServidor + ".ftptemp");

		GestorFTP.download(getFtpActualUrl(),// URL del FTP
				getFtpActualNombreUsuario(),// Nombre de usuario
				getFtpActualPassword(),// Password
				"/"+getNombreAplicacion()+"/" + getCodigoRegionDeDestino() + "/" + getVersionPrincipal()
						+ "/" + nombreArchivoMD5DesdeServidor,// Archivo de
				// origen en
				// servidor.
				rutaArchivoMD5);// Ruta al archivo de destino

		return rutaArchivoMD5;
	}

	/**
	 * Obtiene y retorna una lista con los archivos que deben descargarse desde
	 * el servidor FTP, es decir, los archivos que no están sincronizados con
	 * los archivos locales.
	 * 
	 * @param directorioActual
	 *            . El directorio donde actualmente se está ejecutando el
	 *            programa.
	 * @return lista con los archivos que se deben descargar.
	 */
	public static LinkedList<File> obtenerListaArchivosADescargar(
			File directorioActual) {
		File archivoMD5 = obtenerArchivoConIndicesMD5DesdeServidorFTP(directorioActual);

		LinkedList<File> listaArchivosADescargar = new LinkedList<File>();

		try {
			String texto = "";
			FileReader fr = new FileReader(archivoMD5);
			BufferedReader entrada = new BufferedReader(fr);
			String linea;
			String nombreArchivo;
			String codigoMD5;
			int posicionCorte;
			while ((linea = entrada.readLine()) != null) {
				posicionCorte = linea.indexOf(":");
				codigoMD5 = linea.substring(posicionCorte + 1);
				nombreArchivo = linea.substring(0, posicionCorte);

				texto += linea + "  \n";
				if (!estaActualizado(directorioActual, nombreArchivo, codigoMD5)) {
					listaArchivosADescargar.add(new File(nombreArchivo));
				}
			}
			entrada.close();
			fr.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		cantidadTotalArchivos = listaArchivosADescargar.size();
		return listaArchivosADescargar;
	}

	/**
	 * Encripta una cadena usando la clave pública definida.
	 * 
	 * @return cadena de entrada encriptada según la clave privada.
	 */
	public static String encriptarString(String cadenaAEncriptar) {
		SecretKeySpec clavePrivada = new SecretKeySpec(stringClavePrivada
				.getBytes(), algoritmoEncriptacionClavePrivada);
		Encriptadora encriptador = new Encriptadora("AES", clavePrivada);
		String passwordEncriptada = encriptador.encriptar(cadenaAEncriptar);
		return passwordEncriptada;
	}

	/**
	 * Desencripta una cadena usando la clave pública definida.
	 * 
	 * @return cadena de entrada desencriptada según la clave privada.
	 */
	public static String desencriptarString(String cadenaADesencriptar) {
		SecretKeySpec clavePrivada = new SecretKeySpec(stringClavePrivada
				.getBytes(), algoritmoEncriptacionClavePrivada);
		Encriptadora encriptador = new Encriptadora("AES", clavePrivada);
		String passwordDesencriptada = encriptador
				.desencriptar(cadenaADesencriptar);
		return passwordDesencriptada;
	}

	public static void generarArchivoDeOpcionesDeActualizacion() {

		try {

			File archivoDeOpcionesDeActualizacion = new File(System
					.getProperty("user.dir")
					+ File.separator + "GestorActualizaciones.ini");
			FileWriter fw = new FileWriter(archivoDeOpcionesDeActualizacion);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);

			salida.println("FTP_PRINCIPAL_URL" + "|" + ftpPrincipalUrl);

			salida.println("FTP_PRINCIPAL_USER" + "|"
					+ ftpPrincipalNombreUsuario);

			salida.println("FTP_PRINCIPAL_PASSWORD" + "|"
					+ ftpPrincipalPassword);

			salida.println("FTP_SECUNDARIO_URL" + "|" + ftpSecundarioUrl);

			salida.println("FTP_SECUNDARIO_USER" + "|"
					+ ftpSecundarioNombreUsuario);

			salida.println("FTP_SECUNDARIO_PASSWORD" + "|"
					+ ftpSecundarioPassword);

			salida.println("CODIGO_REGION_DESTINO" + "|"
					+ codigoRegionDeDestino);

			salida.println("VERSION_PRINCIPAL" + "|" + versionPrincipal);

			salida.println("NOMBRE_APLICACION" + "|" + nombreAplicacion);

			salida.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * Comprueba si hay actualizaciones de los archivos locales en el servidor
	 * FTP.
	 * 
	 * @return true si hay actualizaciones disponibles en el servidor. False si
	 *         no las hay o si no se pudo conectar al servidor.
	 */
	public static boolean hayNuevaVersionDelPrograma(File directorioActual) {
		try {

			// GestorActualizacionesUtil.establecerServidorAUtilizar();

			File rutaArchivoMD5Servidor = GestorActualizacionesUtil
					.obtenerArchivoConIndicesMD5DesdeServidorFTP(directorioActual);
			File rutaArchivoMD5Local = new File(directorioActual
					.getAbsolutePath()
					+ File.separator + "files.md5");

			if (rutaArchivoMD5Local.exists()) {// El archivo .md5 local existe,
				// entonces comparamos con el
				// que llegó desde el servidor.
				String md5ArchivoLocal = GestorActualizacionesUtil
						.getMD5(rutaArchivoMD5Local);
				String md5ArchivoServidor = GestorActualizacionesUtil
						.getMD5(rutaArchivoMD5Servidor);

				rutaArchivoMD5Servidor.delete();

				if (md5ArchivoLocal.equals(md5ArchivoServidor)) {
					System.out
							.println("EL ARCHIVO MD5 LOCAL EXISTE Y ES IGUAL AL DEL SERVIDOR, VEREMOS SI LOS ARCHIVOS ESTÁN INTACTOS.");
					/**
					 * Los archivos md5 son iguales. Ahora tenemos que ver si
					 * todos los archivos correspondientes a la versión actual
					 * están efectivamente en el disco duro.
					 */

					if (GestorActualizacionesUtil
							.obtenerListaArchivosADescargar(directorioActual)
							.isEmpty()) {
						return false;
					} else {
						return true;
					}

				} else {//
					System.out
							.println("EL ARCHIVO MD5 LOCAL EXISTE Y ES DIFERENTE AL DEL SERVIDOR, HAY QUE ACTUALIZAR.");
					return true;
				}
			} else {
				return true;// El archivo md5 local no existe, hay que
				// actualizar.
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static void actualizarGestorActualizaciones() {
		File gestorAnterior = new File(System.getProperty("user.dir")
				+ File.separator + "GestorActualizaciones.jar");
		File gestorNuevo = new File(System.getProperty("user.dir")
				+ File.separator + "GestorActualizaciones.jar.ftptemp");

		if (gestorNuevo.exists()) {// Si había gestor anterior, lo borra y lo
			// reemplaza por el nuevo.
			gestorAnterior.delete();
			gestorNuevo.renameTo(gestorAnterior);
		}
	}

	public static void cargarParametrosDeActualizacionDesdeArchivo(
			File directorioActual) {

		File archivoParametrosActualizacion = new File(directorioActual
				.getAbsolutePath()
				+ File.separator + "GestorActualizaciones.ini");

		try {

			FileReader fr = new FileReader(archivoParametrosActualizacion);
			BufferedReader entrada = new BufferedReader(fr);
			String linea;
			String valorParametro;
			int posicionCorte;
			while ((linea = entrada.readLine()) != null) {
				posicionCorte = linea.indexOf("|");
				valorParametro = linea.substring(posicionCorte + 1);

				if (linea.substring(0, posicionCorte).equalsIgnoreCase(
						"FTP_PRINCIPAL_URL")) {

					ftpPrincipalUrl = valorParametro;
				}
				if (linea.substring(0, posicionCorte).equalsIgnoreCase(
						"FTP_PRINCIPAL_USER")) {

					ftpPrincipalNombreUsuario = valorParametro;
				}
				if (linea.substring(0, posicionCorte).equalsIgnoreCase(
						"FTP_PRINCIPAL_PASSWORD")) {

					ftpPrincipalPassword = valorParametro;
				}

				if (linea.substring(0, posicionCorte).equalsIgnoreCase(
						"FTP_SECUNDARIO_URL")) {

					ftpSecundarioUrl = valorParametro;
				}

				if (linea.substring(0, posicionCorte).equalsIgnoreCase(
						"FTP_SECUNDARIO_USER")) {

					ftpSecundarioNombreUsuario = valorParametro;
				}

				if (linea.substring(0, posicionCorte).equalsIgnoreCase(
						"FTP_SECUNDARIO_PASSWORD")) {

					ftpSecundarioPassword = valorParametro;
				}

				if (linea.substring(0, posicionCorte).equalsIgnoreCase(
						"CODIGO_REGION_DESTINO")) {

					codigoRegionDeDestino = valorParametro;
				}

				if (linea.substring(0, posicionCorte).equalsIgnoreCase(
						"VERSION_PRINCIPAL")) {

					versionPrincipal = valorParametro;
				}

				if (linea.substring(0, posicionCorte).equalsIgnoreCase(
						"NOMBRE_APLICACION")) {

					nombreAplicacion = valorParametro;
				}

			}
			entrada.close();
			fr.close();

		} catch (FileNotFoundException ex) {
			GestorActualizaciones
					.setException(ex,
							"No se encontró el archivo de parámetros GestionActualizaciones.ini");

		} catch (SecurityException ex) {
			GestorActualizaciones.setException(ex, "Error de Seguridad.");
		} catch (IOException ex) {
			GestorActualizaciones.setException(ex, "Error de Entrada/Salida.");
		}

	}

	public static boolean ejecutarActualizacionDeArchivos(
			File directorioDestinoArchivos) {

		File directorioDestinoRaiz = directorioDestinoArchivos;

		String rutaDelArchivoDentroDeRaizFTP = new String();
		LinkedList<File> listaArchivosADescargar = new LinkedList<File>();

		listaArchivosADescargar = GestorActualizacionesUtil
				.obtenerListaArchivosADescargar(directorioDestinoArchivos);

		for (int i = 0; listaArchivosADescargar.size() > i; i++) {
			System.out.println("Archivo a actualizar: "
					+ listaArchivosADescargar.get(i));

			/*
			 * If: ¿Es un archivo en una subcarpeta del FTP?
			 */
			if (listaArchivosADescargar.get(i).toString().startsWith("\\")) {
				rutaDelArchivoDentroDeRaizFTP = listaArchivosADescargar.get(i)
						.toString().substring(
								0,
								listaArchivosADescargar.get(i).toString()
										.lastIndexOf("\\"));
				/*
				 * Para que quede su dirección compatible con el formato que
				 * necesita para operar el gestor FTP, le borramos la "/"
				 * inicial y le agregamos una "/" al final.
				 */
				rutaDelArchivoDentroDeRaizFTP = rutaDelArchivoDentroDeRaizFTP
						.substring(1, rutaDelArchivoDentroDeRaizFTP.length());

				rutaDelArchivoDentroDeRaizFTP = rutaDelArchivoDentroDeRaizFTP
						.concat("/");

				/*
				 * La ruta de destino también cambiará, debe ir en una
				 * subcarpeta de la actual.
				 */

				String strDirectorioDestino = directorioDestinoRaiz.toString();

				strDirectorioDestino = strDirectorioDestino.concat("\\"
						+ rutaDelArchivoDentroDeRaizFTP);

				/*
				 * Limpiamos la barra final.
				 */
				strDirectorioDestino = strDirectorioDestino.substring(0,
						strDirectorioDestino.lastIndexOf("/"));

				directorioDestinoArchivos = new File(strDirectorioDestino);

			}

			obtenerArchivoDesdeServidorFTP(listaArchivosADescargar.get(i)
					.getName(), rutaDelArchivoDentroDeRaizFTP,
					directorioDestinoArchivos);

		}

		/*
		 * Si se pudo descargar los archivos sin que ocurra una excepción
		 * anteriormente, quiere decir que la actualización está lista para ser
		 * aplicada. Se procede a reemplazar los archivos temporales descargados
		 * del servidor por los archivos nuevos.
		 */
		renombrarArchivosTemporales(directorioDestinoRaiz);

		return true;// se terminó el proceso sin excepciones.
	}

	/**
	 * Recorre en forma recursiva el directorio actual y lista todos sus
	 * archivos.
	 */
	public static void renombrarArchivosTemporales(File directorio) {
		try {

			File[] archivos = directorio.listFiles();

			for (int x = 0; x < archivos.length; x++) {

				if (archivos[x].getName().endsWith(".ftptemp")) {
					File rutaArchivoNoTemporal = new File(archivos[x]
							.getAbsolutePath().substring(
									0,
									archivos[x].getAbsolutePath().lastIndexOf(
											".ftptemp")));
					rutaArchivoNoTemporal.delete();
					archivos[x].renameTo(rutaArchivoNoTemporal);
				}

				if (archivos[x].isDirectory()) {

					renombrarArchivosTemporales(archivos[x]);
				}
			}

		} catch (NullPointerException ex) {
			System.out
					.println("Imposible acceder al directorio: " + directorio);
		}
	}
}
