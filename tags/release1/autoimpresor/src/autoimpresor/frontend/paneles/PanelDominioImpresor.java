package autoimpresor.frontend.paneles;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.Constantes;
import testerGeneral.domain.Dominio;
import testerGeneral.domain.PersonaRestricion;
import autoimpresor.domain.Persona;
import autoimpresor.service.PersonaDefinition;
import frontend.paneles.PanelDominio;
import frontend.ventanas.JInternalFrameTesterGral;

public class PanelDominioImpresor extends PanelDominio{
	
	public PanelDominioImpresor(Dominio dom, JInternalFrameTesterGral jif) {
		super(dom, jif);
	}
	
	public boolean validarUsoDominios()
	{
		PersonaDefinition personaService=(PersonaDefinition)ContextManager.getBizObject("personaService");
				
		try 
		{	
			
			if(dom.getDomClave().equals(Constantes.DOMINIO_CLAVE_SEXO) && dom.getDomTipo().equals(Constantes.DOMINIO_TIPO_SEXO))
			{
				Persona per=new Persona();
				per.setPerSexo(dom.getDomCodigo());
				if(personaService.getAll(per).size()>0)
					return false;	
			}
			else if(dom.getDomClave().equals(Constantes.DOMINIO_CLAVE_TIPO_DOC) && dom.getDomTipo().equals(Constantes.DOMINIO_TIPO_TIPO_DOC))
			{
				Persona per=new Persona();
				per.setPerTipoDoc(dom.getDomCodigo());
				if(personaService.getAll(per).size()>0)
					return false;	
			}

			else if(dom.getDomClave().equals(Constantes.DOMINIO_CLAVE_NACIONALIDAD) && dom.getDomTipo().equals(Constantes.DOMINIO_TIPO_LOCALIDAD))
			{
				Persona per=new Persona();
				per.setPerNacionalidad(dom.getDomCodigo());
				if(personaService.getAll(per).size()>0)
					return false;	
			}
			else if(dom.getDomClave().equals(Constantes.DOMINIO_CLAVE_GRUPO_SAN) && dom.getDomTipo().equals(Constantes.DOMINIO_TIPO_GRUPO_SAN))
			{
				Persona per=new Persona();
				per.setPerGrupoSanguineo(dom.getDomCodigo());
				if(personaService.getAll(per).size()>0)
					return false;	
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
			
		return true;
	}
}
