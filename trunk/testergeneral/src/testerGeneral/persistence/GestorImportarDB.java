/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testerGeneral.persistence;

import java.io.File;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import org.apache.derby.drda.NetworkServerControl;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import testerGeneral.business.ContextManager;

public class GestorImportarDB implements Runnable {

	public static String rutaOrigenBackup;

	public static void setRutaOrigenBackup(String rutaOrigen) {
		rutaOrigenBackup = rutaOrigen;
	}

	public static String getRutaOrigenBackup() {
		return rutaOrigenBackup;

	}

	public void run() {

		try {
			
			//NetworkServerControl serverControl = getNetworkServerControl();
			
			DriverManagerDataSource dataSource = (DriverManagerDataSource) ContextManager
					.getBizObject("dataSource");
			
			try {
					
				DriverManager.getConnection(dataSource.getUrl()+";shutdown=true");
								
			} catch (Exception e) {
				
				
			}

			String dbURL = dataSource.getUrl()+ ";restoreFrom="
					+ GestorImportarDB.getRutaOrigenBackup() + File.separatorChar
					+ "testerGeneral";

			System.out.println(dbURL);
			Connection conexion = DriverManager.getConnection(dbURL);
			conexion.close();
						
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	/**public static org.apache.derby.drda.NetworkServerControl getNetworkServerControl () {
		
		NetworkServerControl serverControl;
		
			try {
				serverControl = new NetworkServerControl(InetAddress.getByName("localhost"),1527);
				return serverControl;
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			return null;
	}
	
*/
	
}