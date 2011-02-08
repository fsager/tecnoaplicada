package testerGeneral.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.derby.impl.sql.compile.GetCurrentConnectionNode;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import testerGeneral.business.ContextManager;
import testerGeneral.persistence.backup.GestorDBBackup;

public class ConexionManagerTesterGeneral extends org.springframework.jdbc.datasource.SingleConnectionDataSource{
	
	public ConexionManagerTesterGeneral(){
		super();
		setMyConnection();
	}
	
	public void setMyConnection()
	{
		
		File archivoDeConexionDBRemota = new File(System.getProperty("user.dir")+ File.separator + "db_param.cfg");

		if (archivoDeConexionDBRemota.exists() && archivoDeConexionDBRemota.isFile() && archivoDeConexionDBRemota.length() > 0) 
		{
			 setConexionRemota();		
		}
		else
		{
			setConexionLocal();
			
			/*super.setUrl("jdbc:derby://localhost:1527/C:\\programacion\\Workspaces3\\Autoimpresor\\db\\autoimpresor");
			super.setUsername("autoimpresor");
			super.setPassword("autoimpresor");
			super.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
			
			
			super.setUrl("jdbc:derby://localhost:1527/C:\\programacion\\Workspaces3\\TesterGeneral\\db\\testerGeneral");
			super.setUsername("testerGeneral");
			super.setPassword("testerGeneral");
			super.setDriverClassName("org.apache.derby.jdbc.ClientDriver");*/
			
			
			
		}
	}

	@Override
	public Connection getConnection() throws SQLException {
		setMyConnection();
		return super.getConnection();
	}
	
	public void setConexionRemota()
	{			
		String arrayParametros[] = GestorDBBackup.cargarParametrosDeActualizacionDesdeArchivo(new File(System.getProperty("user.dir")));			
		String urlConexion = "jdbc:derby://" + arrayParametros[0]+ ":" + arrayParametros[1] + "/"+ arrayParametros[4];
		super.setUrl(urlConexion);
		super.setUsername(arrayParametros[2]);
		super.setPassword(arrayParametros[3]);
		super.setDriverClassName(arrayParametros[5]);
	}
	
	public void setConexionLocal()
	{
		super.setUrl("jdbc:derby:db\\testerGeneral");
		super.setUsername("testerGeneral");
		super.setPassword("testerGeneral");
		super.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
	}
	
	/*public void destroy()
	{
		super.destroy();
		try
		{
			try
			{
				super.setUrl("jdbc:derby:;shutdown=true");
				this.getConnection();
				
			}
			catch(java.sql.SQLException e)
			{	
				this.initConnection();
				e.printStackTrace();
			}
		}
		catch(Exception ex)
		{
			throw new RuntimeException(ex);
		}
	}*/
}

