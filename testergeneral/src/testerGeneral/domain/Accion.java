package testerGeneral.domain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Accion {
	Method mtAccion;
	Object object;
	Object parameter1;
	Object parameter2;
	
	public Accion(Method mtAccion,Object object,Object parameter1,Object parameter2)
	{
		this.mtAccion=mtAccion;
		this.object=object;
		this.parameter1=parameter1;
		this.parameter2=parameter2;
		
	}
	
	public Object invoke()
	{
		try
		{
			System.out.println("mtAccion: "+mtAccion.getName());
			if(parameter2!=null)
				return mtAccion.invoke(object,parameter1,parameter2);
			else if(parameter1!=null)
				return mtAccion.invoke(object,parameter1);
			else
				return mtAccion.invoke(object);
			
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}		
}
