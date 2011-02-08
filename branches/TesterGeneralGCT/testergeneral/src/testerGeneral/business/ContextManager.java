package testerGeneral.business;



import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import testerGeneral.domain.Propiedad;
import testerGeneral.service.PropiedadDefinition;


public class ContextManager
{
    private static ApplicationContext delegate;
    private static java.sql.Connection conn = null;
    
    static {
    	String ctxs[]=new String [1];
    	ctxs[0]="applicationContext.xml";
    	delegate = new ClassPathXmlApplicationContext(ctxs);    	
    }
    
	/**
	 * Retorna la implementación de un objeto de negocio. Puede ser un objeto
	 * implementado por el servicio o una implementación Java.
	 * 
	 * @param p_business_object    Nombre del objeto de negocio que se desea obtener.
	 */
	public static Object getBizObject(String p_business_object)
	{
		return (delegate).getBean(p_business_object);
	}
	
	public static java.sql.Connection getCurrentConnection () throws SQLException
	{
		if (conn==null || conn.isClosed())
		{
			DataSource datasource =(DataSource) ContextManager.getBizObject("dataSource");
			conn = datasource.getConnection();
		}
		
		return conn;
	}

	public static java.sql.Connection getConnection () throws SQLException
	{
		
		return ContextManager.getCurrentConnection();
	}


	@Override
	protected void finalize() throws Throwable {
		try {
			conn.close();
		}
		catch (Exception e) {
		}
		super.finalize();
	}
	
    public static String getProperty (String key) {
    	try {
	    	PropiedadDefinition propiedadService = (PropiedadDefinition) ContextManager.getBizObject("propiedadService");
	    	Propiedad property=propiedadService.get(key);
	    	if (property == null)
	    		throw new RuntimeException ("Propiedad "+key+" no definida en el sistema");
	    	else
	    		return property.getPropValor();
    		
    	}
    	catch (Exception e) {
    		throw new RuntimeException(e);
    	}
    }
    
    public static Propiedad getPropertyObj (String key) {
    	try {
	    	PropiedadDefinition propiedadService = (PropiedadDefinition) ContextManager.getBizObject("propiedadService");
	    	Propiedad property=propiedadService.get(key);
	    	if (property == null)
	    		throw new RuntimeException ("Propiedad "+key+" no definida en el sistema");
	    	else
	    		return property;
    		
    	}
    	catch (Exception e) {
    		throw new RuntimeException(e);
    	}
    }

    public static void updatePropiedad (Propiedad pro) {
    	try {
	    	PropiedadDefinition propiedadService = (PropiedadDefinition) ContextManager.getBizObject("propiedadService");
	    	propiedadService.update(pro);	    	
    	}
    	catch (Exception e) {
    		throw new RuntimeException(e);
    	}
    }

}
