package opcionesmultiples.db;

import testerGeneral.db.ConexionManagerTesterGeneral;

public class ConexionManagerOpcionesMultiples extends ConexionManagerTesterGeneral{
	
	public ConexionManagerOpcionesMultiples(){
		super();
	}
	
	public void setConexionLocal()
	{
		super.setUrl("jdbc:derby:db\\opcionesMultiples");
		super.setUsername("opcionesMultiples");
		super.setPassword("opcionesMultiples");
		super.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
	}
}
