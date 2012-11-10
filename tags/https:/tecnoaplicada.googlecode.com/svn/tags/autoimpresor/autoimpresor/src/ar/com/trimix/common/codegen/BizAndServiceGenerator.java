package ar.com.trimix.common.codegen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class BizAndServiceGenerator {
	public static String p_temp_path=".\\templates\\";
	public static String p_path_biz=".\\builds\\business\\";
	public static String p_path_srv=".\\builds\\service\\";
	public static String p_path_srv_impl=".\\builds\\service\\impl\\";
	
	public static void makeClass (String root_package, String className) throws Exception
	{
		p_path_biz=new File(p_path_biz).getCanonicalPath()+File.separator;
		p_path_srv=new File(p_path_srv).getCanonicalPath()+File.separator;
		p_path_srv_impl=new File(p_path_srv_impl).getCanonicalPath()+File.separator;
		makeServiceDefinition (root_package, className);
		makeServiceImpl (root_package, className);
		makeBusiness (root_package, className);
	}
	
	private static void makeServiceDefinition (String root_package, String className) throws Exception
	{
		FileInputStream fis=new FileInputStream (p_temp_path+"ServiceDefinition.txt");
		int cant=fis.available();
		byte byt[]=new byte [cant];
		fis.read(byt);
		fis.close();
		String template=new String (byt);
		template=template.replaceAll("-##root_package##-", root_package);
		template=template.replaceAll("-##ClassName##-", className);
		
		FileOutputStream fos=new FileOutputStream (p_path_srv+className+"Definition.java");
		fos.write (template.getBytes());
		fos.close();
	}
	
	private static void makeServiceImpl (String root_package, String className) throws Exception
	{
		FileInputStream fis=new FileInputStream (p_temp_path+"ServiceImpl.txt");
		int cant=fis.available();
		byte byt[]=new byte [cant];
		fis.read(byt);
		fis.close();
		String template=new String (byt);
		template=template.replaceAll("-##root_package##-", root_package);
		template=template.replaceAll("-##ClassName##-", className);
		
		FileOutputStream fos=new FileOutputStream (p_path_srv_impl+className+"Service.java");
		fos.write (template.getBytes());
		fos.close();
	}
	
	private static void makeBusiness (String root_package, String className) throws Exception
	{
		FileInputStream fis=new FileInputStream (p_temp_path+"BusinessObject.txt");
		int cant=fis.available();
		byte byt[]=new byte [cant];
		fis.read(byt);
		fis.close();
		String template=new String (byt);
		template=template.replaceAll("-##root_package##-", root_package);
		template=template.replaceAll("-##ClassName##-", className);
		
		FileOutputStream fos=new FileOutputStream (p_path_biz+className+"Biz.java");
		fos.write (template.getBytes());
		fos.close();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BizAndServiceGenerator.makeClass("ar.com.binside.common", "ClaveDominio");
			BizAndServiceGenerator.makeClass("ar.com.binside.common", "Dominio");
			BizAndServiceGenerator.makeClass("ar.com.binside.common", "Empresa");
			BizAndServiceGenerator.makeClass("ar.com.binside.common", "IdiomasInstalados");
			BizAndServiceGenerator.makeClass("ar.com.binside.common", "Mensaje");
			BizAndServiceGenerator.makeClass("ar.com.binside.common", "Permisos");
			BizAndServiceGenerator.makeClass("ar.com.binside.common", "Persona");
			BizAndServiceGenerator.makeClass("ar.com.binside.common", "Propiedad");
			BizAndServiceGenerator.makeClass("ar.com.binside.common", "Rol");
			BizAndServiceGenerator.makeClass("ar.com.binside.common", "RolUsuario");
			BizAndServiceGenerator.makeClass("ar.com.binside.common", "TraduccionDominio");
			BizAndServiceGenerator.makeClass("ar.com.binside.common", "Usuario");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println ("TERMINO");
	}

}
