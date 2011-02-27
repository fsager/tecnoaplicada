package ar.com.tecnologiaaplicada.presentation.converters;

import org.zkoss.zkplus.databind.TypeConverter;

import ar.com.tecnologiaaplicada.business.ContextManager;
import ar.com.tecnologiaaplicada.domain.Dominio;
import ar.com.tecnologiaaplicada.service.DominioDefinition;

public class DomainConverter implements TypeConverter {
	DominioDefinition  dominioService= (DominioDefinition) ContextManager.getBizObject("dominioService");

	//return sadDominioService.getDominio("ACTIVO", ""+val, new Long (1)).getDomTexto();
	
	public Object coerceToBean(java.lang.Object val,org.zkoss.zk.ui.Component comp){
		try
		{
			String domain=(String)comp.getAttribute("domain");
			Dominio domExample=new Dominio();
			domExample.setDomClave(domain);
			//domExample.setDomCodigo(String.valueOf((Long)val));
			domExample.setDomValorMostrar((String)val);
			
			domExample=(Dominio)dominioService.getAll(domExample).get(0);
			return Long.valueOf(domExample.getDomCodigo());
		}			
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
 
	public Object coerceToUi(java.lang.Object val, org.zkoss.zk.ui.Component comp) {
		
		try
		{
			String domain=(String)comp.getAttribute("domain");
			Dominio domExample=new Dominio();
			domExample.setDomClave(domain);
			domExample.setDomCodigo(String.valueOf((Long)val));
			
			domExample=(Dominio)dominioService.getAll(domExample).get(0);
			return domExample.getDomValorMostrar();
		}			
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}