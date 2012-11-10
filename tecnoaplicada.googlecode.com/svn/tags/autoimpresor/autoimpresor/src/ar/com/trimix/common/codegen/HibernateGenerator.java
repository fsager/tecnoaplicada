package ar.com.trimix.common.codegen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class HibernateGenerator {

	public static String p_temp_path=".\\templates\\";
	public static String p_path=".\\builds\\impl\\";
	public static String p_path_dao=".\\builds\\dao\\";
	
	public static void makeClass (String root_package, String className) throws Exception
	{
		p_path=new File(p_path).getCanonicalPath()+File.separator;
		p_path_dao=new File(p_path_dao).getCanonicalPath()+File.separator;
//		System.out.println ("HibernateGenerator: "+className);
		makeDao (root_package, className);
		makeImpl (root_package, className);
	}
	
	private static void makeDao (String root_package, String className) throws Exception
	{
		FileInputStream fis=new FileInputStream (p_temp_path+"GenericDao.txt");
		int cant=fis.available();
		byte byt[]=new byte [cant];
		fis.read(byt);
		fis.close();
		String template=new String (byt);
		template=template.replaceAll("-##root_package##-", root_package);
		template=template.replaceAll("-##ClassName##-", className);
		
		FileOutputStream fos=new FileOutputStream (p_path_dao+className+"Dao.java");
		fos.write (template.getBytes());
		fos.close();
	}
	
	private static void makeImpl (String root_package, String className) throws Exception
	{
		FileInputStream fis=new FileInputStream (p_temp_path+"HibernateHome.txt");
		int cant=fis.available();
		byte byt[]=new byte [cant];
		fis.read(byt);
		fis.close();
		String template=new String (byt);
		template=template.replaceAll("-##root_package##-", root_package);
		template=template.replaceAll("-##ClassName##-", className);
		
		FileOutputStream fos=new FileOutputStream (p_path+className+"Home.java");
		fos.write (template.getBytes());
		fos.close();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HibernateGenerator.makeClass("ar.com.binside.common", "ClaveDominio");
			HibernateGenerator.makeClass("ar.com.binside.common", "Dominio");
			HibernateGenerator.makeClass("ar.com.binside.common", "Empresa");
			HibernateGenerator.makeClass("ar.com.binside.common", "IdiomasInstalados");
			HibernateGenerator.makeClass("ar.com.binside.common", "Mensaje");
			HibernateGenerator.makeClass("ar.com.binside.common", "Permisos");
			HibernateGenerator.makeClass("ar.com.binside.common", "Persona");
			HibernateGenerator.makeClass("ar.com.binside.common", "Propiedad");
			HibernateGenerator.makeClass("ar.com.binside.common", "Rol");
			HibernateGenerator.makeClass("ar.com.binside.common", "RolUsuario");
			HibernateGenerator.makeClass("ar.com.binside.common", "TraduccionDominio");
			HibernateGenerator.makeClass("ar.com.binside.common", "Usuario");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println ("TERMINO");
	}

}
