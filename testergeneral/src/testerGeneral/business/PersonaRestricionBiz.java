package testerGeneral.business;

import java.util.List;
import java.util.Set;

import frontend.utils.Util;

import testerGeneral.domain.Persona;
import testerGeneral.domain.PersonaRestricion;
import testerGeneral.persistence.PersonaRestricionDao;
import testerGeneral.service.PersonaRestricionDefinition;

public class PersonaRestricionBiz implements PersonaRestricionDefinition {
	PersonaRestricionDao dao;
	
	public void setDao (PersonaRestricionDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(PersonaRestricion p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public PersonaRestricion get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(PersonaRestricion p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(PersonaRestricion p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(PersonaRestricion p_domain) throws Exception {
		dao.update(p_domain);
	}

	public String getOtrasAflicciones(Persona p_domain) throws Exception {
		String otrasAflicciones=new String();
		List<PersonaRestricion> restricciones=(List)Util.getPersonaRestriciones(p_domain);

		
		String[] restriccionesADescartar={"Usa Lentes de Contacto","Lentes de Contacto","Usa Anteojos","Anteojos"};

		for(PersonaRestricion perRestriccion:restricciones)
		{
			boolean agregar=true;
			for(int i=0;i<restriccionesADescartar.length;i++)
			{
				if(perRestriccion.getDescripcion().equals(restriccionesADescartar[i]))
				{
					agregar=false;
					break;
				}
			}
			
			if(agregar)
				otrasAflicciones+=perRestriccion.getDescripcion()+", ";
		}
		
		if(otrasAflicciones.lastIndexOf(",")!=-1)
			otrasAflicciones=otrasAflicciones.substring(0,otrasAflicciones.lastIndexOf(","));
		
		return otrasAflicciones;
	}
}
