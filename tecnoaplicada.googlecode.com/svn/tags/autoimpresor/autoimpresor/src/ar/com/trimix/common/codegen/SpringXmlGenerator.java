package ar.com.trimix.common.codegen;

import java.io.PrintStream;

public class SpringXmlGenerator {

	static StringBuffer sb_dao=new StringBuffer();;
	static StringBuffer sb_biz=new StringBuffer();;
	static StringBuffer sb_srv=new StringBuffer();;

	public SpringXmlGenerator ()
	{
		sb_dao.append("    <!-- Add SECURITY DAOs here -->\r\n");
		sb_biz.append("    <!-- Add SECURITY Business here -->\r\n");
		sb_srv.append("    <!-- Add SECURITY Service here -->\r\n");
	}
	
	public void makeClass (String root_package, String className) throws Exception
	{
		String initCap=className.substring(0,1).toLowerCase()+className.substring(1, className.length());

		sb_dao.append("    <bean id=\""+initCap+"Dao\" class=\""+root_package+".persistence.impl."+className+"Home\">\r\n");
		sb_dao.append("        <property name=\"sessionFactory\" ref=\"sessionFactory\"/>\r\n");
		sb_dao.append("    </bean>\r\n");
	    
		sb_biz.append("    <bean id=\""+initCap+"Business\" class=\""+root_package+".business."+className+"Biz\">\r\n");
		sb_biz.append("        <property name=\"dao\" ref=\""+initCap+"Dao\"/>\r\n");
		sb_biz.append("    </bean>\r\n");
	    
		sb_biz.append("    <bean id=\""+initCap+"Biz\" parent=\"baseTransactionProxy\">\r\n ");
		sb_biz.append("        <property name=\"target\">\r\n ");
		sb_biz.append("            <ref bean=\""+initCap+"Business\"/> \r\n");
		sb_biz.append("        </property> \r\n");
		sb_biz.append("    </bean>\r\n");

		sb_srv.append("    <bean id=\""+initCap+"Service\" class=\""+root_package+".service.impl."+className+"Service\" scope=\"singleton\" >\r\n");
		sb_srv.append("        <property name=\"businessObject\" ref=\""+initCap+"Biz\"/>\r\n");
		sb_srv.append("    </bean>\r\n");
	}
	
	public void showClass (PrintStream os)
	{
		os.println (sb_dao.toString());
		os.println (sb_biz.toString());
		os.println (sb_srv.toString());
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SpringXmlGenerator sxg=new SpringXmlGenerator();
			
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
