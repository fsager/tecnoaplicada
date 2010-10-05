package testerGeneral.db;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.derby.impl.sql.compile.GetCurrentConnectionNode;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import testerGeneral.business.ContextManager;
import testerGeneral.persistence.backup.GestorDBBackup;

public class ConexionManager extends org.springframework.jdbc.datasource.DriverManagerDataSource{
	
	public ConexionManager(){
		setMyConnection();
	}
	
	public void setMyConnection()
	{
		
		File archivoDeConexionDBRemota = new File(System.getProperty("user.dir")+ File.separator + "db_param.cfg");

		if (archivoDeConexionDBRemota.exists() && archivoDeConexionDBRemota.isFile() && archivoDeConexionDBRemota.length() > 0) 
		{
			String arrayParametros[] = GestorDBBackup.cargarParametrosDeActualizacionDesdeArchivo(new File(System.getProperty("user.dir")));			
			String urlConexion = "jdbc:derby://" + arrayParametros[0]+ ":" + arrayParametros[1] + "/"+ arrayParametros[4];
			super.setUrl(urlConexion);
			super.setUsername(arrayParametros[2]);
			super.setPassword(arrayParametros[3]);
			super.setDriverClassName(arrayParametros[5]);
			
			//super.getConnection();
		}
		else
		{
			super.setUrl("jdbc:derby:db\\autoimpresor");
			super.setUsername("autoimpresor");
			super.setPassword("autoimpresor");
			super.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
		}
	}

	@Override
	public Connection getConnection() throws SQLException {
		setMyConnection();
		
		return super.getConnection();
	}
	
	
	
	/*public void setConexionRemota() throws Exception
	{
			
		String urlConexion = "jdbc:derby://" + arrayParametros[0]+ ":" + arrayParametros[1] + "/"+ arrayParametros[4];
		datasource.setUrl(urlConexion);
		datasource.setUsername(arrayParametros[2]);
		datasource.setPassword(arrayParametros[3]);
		datasource.setDriverClassName(arrayParametros[5]);
	
		datasource.getConnection();
	}
	
	public void setConexionLocal()
	{
		DriverManagerDataSource datasource = (DriverManagerDataSource) ContextManager.getBizObject("dataSource");
		datasource.setUrl("jdbc:derby:db\\autoimpresor");
		datasource.setUsername("autoimpresor");
		datasource.setPassword("autoimpresor");
		datasource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
	}*/
}
