/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testerGeneral.persistence;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import testerGeneral.business.ContextManager;

public class GestorExportarDB implements Runnable {

	public static String rutaDestinoBackup;
	public static String extensionArchivosDeBackup = ".sql";
	private String fileDestino;
	private boolean finish=false;

	public static void setRutaDestinoBackup(String rutaDestino) {
		rutaDestinoBackup = rutaDestino;
	}

	public static String getRutaDestinoBackup() {
		return rutaDestinoBackup;
	}



	public void run() {

		try {

			Connection conexion = ContextManager.getConnection();

			String directorioBackup = GestorExportarDB.getRutaDestinoBackup();
			CallableStatement cs = conexion
					.prepareCall("CALL SYSCS_UTIL.SYSCS_BACKUP_DATABASE(?)");
			cs.setString(1, directorioBackup);
			cs.execute();
			cs.close();
			System.out.println("Se guardó una copia de la base de datos en: " + directorioBackup);
			
			//Se comprimen los datos a un archivo .zip
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyss");
			String fileOrigen = directorioBackup + File.separator
					+ ContextManager.getProperty("SISTEMA.NOMBRE.PROGRAMA");
			fileDestino = directorioBackup + File.separator
					+ ContextManager.getProperty("SISTEMA.NOMBRE.PROGRAMA") + sdf.format(new Date()) + ".zip";

			testerGeneral.persistence.impl.Util.zipDir(fileDestino, fileOrigen);
			testerGeneral.persistence.impl.Util.deleteDir(new File(fileOrigen));
			
			finish=true;

		} catch (SQLException sqlExcep) {
			sqlExcep.printStackTrace();

		}

	}
	
	public boolean isFinish() {
		return finish;
	}
	
	public String getFileDestino() {
		return fileDestino;
	}

}
