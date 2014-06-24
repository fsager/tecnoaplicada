package testerGeneral.persistence.backup;

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
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.Propiedad;
import testerGeneral.persistence.GestorExportarDB;
import testerGeneral.seguridad.Encriptadora;
import testerGeneral.service.PropiedadDefinition;
import actualizaciones.GestorActualizacionesUtil;


public class GestorDBBackup implements Runnable {
	
	private final static String stringClavePrivada = new String(
			"czbmrndoritlekaz");
	private final static String algoritmoEncriptacionClavePrivada = new String(
			"AES");	

	public void run() {
		realizarBackupAutomatico();
	}

	/**
	 * Realiza un backup automático de la base de datos del sistema a las
	 * ubicaciones especificadas en DIRECTORIO.BACKUP.PRIMARIO y en
	 * DIRECTORIO.BACKUP.SECUNDARIO (si aplica).
	 */
	public static void realizarBackupAutomatico() {
		PropiedadDefinition propiedadService = (PropiedadDefinition) ContextManager
		.getBizObject("propiedadService");
		if (ContextManager.getProperty("SISTEMA.BACKUP.AUTOMATICO.ACTIVADO")
				.equals("S")) {

			if (cumplioPlazoMaximoBackup()) {// Es momento de hacer el backup
												// automático.
				try {
					GestorExportarDB gestorExportar = new GestorExportarDB();
					GestorExportarDB.setRutaDestinoBackup(ContextManager
							.getProperty("DIRECTORIO.BACKUP.PRIMARIO"));
					Thread threadGestorExportar = new Thread(gestorExportar);
					threadGestorExportar.run();
					
					
					//Espero que termine el proceso de exportación
					while(!gestorExportar.isFinish()){
						Thread.sleep(100);
					}
					
					
					upload(gestorExportar.getFileDestino());
					
					
					
					if (ContextManager.getProperty(
							"SISTEMA.BACKUP.SECUNDARIO.SI-NO").equals("S")) {// Backup
																				// secundario
																				// activado.

						System.out.println("SE EJECUTA EL BACKUP SECUNDARIO");
						GestorExportarDB.setRutaDestinoBackup(ContextManager
								.getProperty("DIRECTORIO.BACKUP.SECUNDARIO"));
						Thread threadGestorExportarSecundario = new Thread(
								gestorExportar);
						threadGestorExportarSecundario.run();
					}

					/*
					 * Luego de realizado el backup, se actualiza la fecha de
					 * último backup a la fecha actual.
					 */

					Propiedad propiedadFechaUltimoBackup = new Propiedad();
					propiedadFechaUltimoBackup
							.setPropClave("SISTEMA.BACKUP.FECHAULTIMOBACKUP");

					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
							"dd-MM-yyyy");
					Date fechaUltimoBackup = new Date();// Fecha de hoy

					String fechaParseada = simpleDateFormat
							.format(fechaUltimoBackup);

					propiedadFechaUltimoBackup.setPropValor(fechaParseada);

					propiedadService.update(propiedadFechaUltimoBackup);
					
					
					
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
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
	
	public static void upload(String fileToUpload) throws Exception
	{

		String server = desencriptarString(ContextManager.getProperty("SISTEMA.FTP_PRINCIPAL_URL"));
        int port = 21;
        String user = desencriptarString(ContextManager.getProperty("SISTEMA.FTP_PRINCIPAL_USER"));
        String pass = desencriptarString(ContextManager.getProperty("SISTEMA.FTP_PRINCIPAL_PASSWORD"));
        String licence=ContextManager.getProperty("LICENCE.NRO");
	 
        FTPClient ftpClient = new FTPClient();
        try {
            	
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
 
            if(licence!=null && !licence.equals("") && !licence.equals(" "))
            {
	            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	            String fileDestination="/backups/"+ContextManager.getProperty("SISTEMA.NOMBRE.PROGRAMA")+"/Licencia_"+licence;
	            createIntermediateFolders(ftpClient,fileDestination);
	            FTPFile[] files=ftpClient.listFiles(fileDestination);
	            
	            if(files.length>=6)
	            {
	            	//Borro el más viejo de los archivos
		            Arrays.sort(files, new Comparator<FTPFile>() {
						@Override
						public int compare(FTPFile o1, FTPFile o2) {
							return o1.getTimestamp().compareTo(o2.getTimestamp());
						}
					});
		            for(int i=0;i<files.length;i++)
		            {
		            	if(!files[i].getName().equals(".") && !files[i].getName().equals(".."))
		            	{
		            		ftpClient.deleteFile(fileDestination+"/"+files[i].getName());
		            		break;
		            	}
		            		
		            }
		            	            	
	            }	            
	
	            File localFile = new File(fileToUpload);
	 
				SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_ss");
				

				
	            String remoteFile = fileDestination+"/"+ContextManager.getProperty("SISTEMA.NOMBRE.PROGRAMA") +"_"+ sdf.format(new Date()) + ".zip";
	            InputStream inputStream = new FileInputStream(localFile);
	
	            ftpClient.storeFile(remoteFile, inputStream);
	            inputStream.close();
            }
            

 
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
	}
	
	
	public static void createIntermediateFolders(FTPClient ftpClient,String fileDestination) throws Exception
	{
		String[] folders=fileDestination.split("/");
		String absolutFolder="";
		for(String folder:folders)
		{
			absolutFolder+="/"+folder;
			if(!ftpClient.changeWorkingDirectory(absolutFolder))
			{
				ftpClient.makeDirectory(absolutFolder);
			}
				
		}
		
	}

	/**
	 * Comprueba si se cumplió el plazo definido en la propiedad
	 * SISTEMA.BACKUP.FECHAULTIMOBACKUP y en tal caso retorna true.
	 * 
	 * @return true si se cumplió el plazo y hay que realizar un backup.
	 */
	private static boolean cumplioPlazoMaximoBackup() {
		try {
			PropiedadDefinition propiedadService = (PropiedadDefinition) ContextManager
			.getBizObject("propiedadService");
			
			Propiedad propiedadFechaUltimoBackup = propiedadService
					.get("SISTEMA.BACKUP.FECHAULTIMOBACKUP");

			Propiedad propiedadCantidadDiasBackupAutomatico = propiedadService
					.get("SISTEMA.BACKUP.AUTOMATICO.CADAXDIAS");

			Propiedad propiedadActivarBackupAutomaticoCadaXDias = propiedadService
					.get("SISTEMA.BACKUP.AUTOMATICO.ACTIVADO");

			String valorPropiedadFechaUltimoBackup = propiedadFechaUltimoBackup
					.getPropValor();

			String valorPropiedadActivarBackupAutomaticoCadaXDias = propiedadActivarBackupAutomaticoCadaXDias
					.getPropValor();

			String valorPropiedadCantidadDiasBackupAutomatico = propiedadCantidadDiasBackupAutomatico
					.getPropValor();

			if (valorPropiedadActivarBackupAutomaticoCadaXDias.equals("S")) {// Está
																				// habilitada
																				// la
																				// opción
																				// de
																				// backup
																				// automático

				SimpleDateFormat formatoDelTexto = new SimpleDateFormat(
						"dd-MM-yyyy");
				java.util.Date fechaUltimoBackup = null;

				fechaUltimoBackup = (java.util.Date) formatoDelTexto
						.parse(valorPropiedadFechaUltimoBackup);// Convierto el
																// String
																// recibido a
																// Date

				DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
				String fechaUltimoBackupString = df.format(fechaUltimoBackup);
				fechaUltimoBackup = df.parse(fechaUltimoBackupString);
				Date fechaActual = new Date();
				String fechaActualString = df.format(fechaActual);
				fechaActual = df.parse(fechaActualString);

				long fechaUltimoBackupMs = fechaUltimoBackup.getTime();
				long fechaActualMs = fechaActual.getTime();
				long diferencia = fechaActualMs - fechaUltimoBackupMs;

				double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
				if (Double.valueOf(valorPropiedadCantidadDiasBackupAutomatico) <= dias) {

					return true;
				}

			}

		} catch (Exception exc) {
			throw new RuntimeException(exc);
		}

		return false;
	}

	/**
	 * Ejecuta una sentencia SQL desde un archivo predeterminado. Si la
	 * sentencia ya había sido ejecutada anteriormente, no se ejecuta de nuevo.
	 */
	public static void ejecutarSentenciaSQL() {
		try {
			PropiedadDefinition propiedadService = (PropiedadDefinition) ContextManager
			.getBizObject("propiedadService");
			File f = new File(System.getProperty("user.dir") + File.separator
					+ "actualizar_db.sql");

			String hashUltimoScriptEjecutado = ContextManager
					.getProperty("SISTEMA.HASH_ULTIMO_ARCHIVO_SCRIPT_EJECUTADO");

			if (f.exists()) {// El archivo actualizar_db.sql existe localmente.
				String hashScriptActual = GestorActualizacionesUtil.getMD5(f);

				if (!hashScriptActual
						.equalsIgnoreCase(hashUltimoScriptEjecutado)) {

					System.out.println("Entro a ejecutar el script sql");

					Connection conn = ContextManager.getConnection();
					FileInputStream iStrArchivoScriptSql = new FileInputStream(
							f);
					FileOutputStream archivoSalidaScript = new FileOutputStream(
							System.getProperty("user.dir") + File.separator
									+ "script.out");

					org.apache.derby.tools.ij.runScript(conn,
							iStrArchivoScriptSql, "CP1252",
							archivoSalidaScript, "CP1252");
					conn.commit();

					Propiedad propiedadHashUltimoArchivoEjecutado = new Propiedad();
					propiedadHashUltimoArchivoEjecutado
							.setPropClave("SISTEMA.HASH_ULTIMO_ARCHIVO_SCRIPT_EJECUTADO");

					propiedadHashUltimoArchivoEjecutado
							.setPropValor(hashScriptActual);

					propiedadService
							.update(propiedadHashUltimoArchivoEjecutado);

				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	

	public static void ejecutarMigracionSQL() {
		try {
			PropiedadDefinition propiedadService = (PropiedadDefinition) ContextManager
			.getBizObject("propiedadService");
			File f = new File(System.getProperty("user.dir") + File.separator
					+ "migracion.sql");

			if (f.exists()) {

					Connection conn = ContextManager.getConnection();
					FileInputStream iStrArchivoScriptSql = new FileInputStream(
							f);
					FileOutputStream archivoSalidaScript = new FileOutputStream(
							System.getProperty("user.dir") + File.separator
									+ "scriptMigrar.out");

					org.apache.derby.tools.ij.runScript(conn,
							iStrArchivoScriptSql, "CP1252",
							archivoSalidaScript, "CP1252");
					conn.commit();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static void generarArchivoDeConexionDBRemota(String direccionIP,
			int puertoConexion, String nombreUsuario, String password,
			String rutaAcceso) {

		try {

			File archivoDeConexionDBRemota = new File(System
					.getProperty("user.dir")
					+ File.separator + "db_param.cfg");
			FileWriter fw = new FileWriter(archivoDeConexionDBRemota);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);

			salida.println("DIRECCION_EQUIPO" + "|" + direccionIP);

			salida.println("PUERTO_CONEXION" + "|" + puertoConexion);

			salida.println("NOMBRE_USUARIO" + "|" + nombreUsuario);

			salida.println("PASSWORD" + "|" + password);

			salida.println("RUTA_EN_DISCO_REMOTO" + "|" + rutaAcceso);

			salida.println("DRIVER_CLASS_NAME" + "|"
					+ "org.apache.derby.jdbc.ClientDriver");

			salida.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * 
	 * @param directorioActual
	 * @return array con seis valores, en este orden: 0: Dirección del equipo a
	 *         conectarse, 1: Puerto de conexión, 2: Nombre de usuario, 3:
	 *         Password, 4: Ruta en disco remoto, 5: Driver Class Name.
	 */
	public static String[] cargarParametrosDeActualizacionDesdeArchivo(
			File directorioActual) {

		String[] arrayParametros = new String[6];

		File archivoDeConexionDBRemota = new File(directorioActual
				.getAbsolutePath()
				+ File.separator + "db_param.cfg");

		try {

			FileReader fr = new FileReader(archivoDeConexionDBRemota);
			BufferedReader entrada = new BufferedReader(fr);
			String linea;
			String valorParametro;
			int posicionCorte;

			while ((linea = entrada.readLine()) != null) {
				posicionCorte = linea.indexOf("|");
				valorParametro = linea.substring(posicionCorte + 1);

				if (linea.substring(0, posicionCorte).equalsIgnoreCase(
						"DIRECCION_EQUIPO")) {
					arrayParametros[0] = valorParametro;
				}
				if (linea.substring(0, posicionCorte).equalsIgnoreCase(
						"PUERTO_CONEXION")) {
					arrayParametros[1] = valorParametro;
				}
				if (linea.substring(0, posicionCorte).equalsIgnoreCase(
						"NOMBRE_USUARIO")) {
					arrayParametros[2] = valorParametro;
				}

				if (linea.substring(0, posicionCorte).equalsIgnoreCase(
						"PASSWORD")) {
					
					SecretKeySpec clavePrivada = new SecretKeySpec(new String(
					"czbmrndoritlekaz").getBytes(), "AES");
					Encriptadora encriptador = new Encriptadora("AES", clavePrivada);
					String desencriptPass = encriptador.desencriptar(valorParametro);
					arrayParametros[3] = desencriptPass;
				}

				if (linea.substring(0, posicionCorte).equalsIgnoreCase(
						"RUTA_EN_DISCO_REMOTO")) {
					arrayParametros[4] = valorParametro;
				}

				if (linea.substring(0, posicionCorte).equalsIgnoreCase(
						"DRIVER_CLASS_NAME")) {
					arrayParametros[5] = valorParametro;
				}
			}
			entrada.close();
			fr.close();

		} catch (FileNotFoundException ex) {

		} catch (SecurityException ex) {

		} catch (IOException ex) {

		}

		return arrayParametros;
	}

}