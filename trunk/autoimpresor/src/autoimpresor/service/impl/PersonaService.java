package autoimpresor.service.impl;

import java.util.List;

import autoimpresor.domain.Persona;
import autoimpresor.service.PersonaDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class PersonaService implements PersonaDefinition {

	protected PersonaDefinition biz;
	
	public PersonaService(){

	}
	
	public void setBusinessObject (PersonaDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(Persona p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public Persona get(Long p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(Persona p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(Persona p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(Persona p_domain) throws Exception {
		biz.update(p_domain);
	}
}