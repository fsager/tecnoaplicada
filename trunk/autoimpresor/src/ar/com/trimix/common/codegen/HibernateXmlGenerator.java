package ar.com.trimix.common.codegen;

import java.io.PrintStream;

public class HibernateXmlGenerator {

	static StringBuffer sb_dao=new StringBuffer();;

	public HibernateXmlGenerator ()
	{
	}
	
	public void makeClass (String root_package, String className) throws Exception
	{
		sb_dao.append("		<mapping resource=\"ar/com/binside/common/persistence/"+className+".hbm.xml\" />\r\n");
	}
	
	public void showClass (PrintStream os)
	{
		os.println (sb_dao.toString());
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HibernateXmlGenerator sxg=new HibernateXmlGenerator();
			
			sxg.makeClass("ar.com.binside.common", "ClaveDominio");
			sxg.makeClass("ar.com.binside.common", "Dominio");
			sxg.makeClass("ar.com.binside.common", "Empresa");
			sxg.makeClass("ar.com.binside.common", "IdiomasInstalados");
			sxg.makeClass("ar.com.binside.common", "Mensaje");
			sxg.makeClass("ar.com.binside.common", "Permisos");
			sxg.makeClass("ar.com.binside.common", "Persona");
			sxg.makeClass("ar.com.binside.common", "Propiedad");
			sxg.makeClass("ar.com.binside.common", "Rol");
			sxg.makeClass("ar.com.binside.common", "RolUsuario");
			sxg.makeClass("ar.com.binside.common", "TraduccionDominio");
			sxg.makeClass("ar.com.binside.common", "Usuario");

			sxg.showClass(System.out);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println ("TERMINO");
	}

}
