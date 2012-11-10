package autoimpresor.db;

import testerGeneral.db.ConexionManagerTesterGeneral;

public class ConexionManagerAutoimpresor extends ConexionManagerTesterGeneral{
	
	public ConexionManagerAutoimpresor(){
		super();
	}
	
	public void setConexionLocal()
	{
		super.setUrl("jdbc:derby:db\\autoimpresor");
		super.setUsername("autoimpresor");
		super.setPassword("autoimpresor");
		super.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
	}
}
