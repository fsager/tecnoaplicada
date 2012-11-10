package autoimpresor.service.impl;

import java.util.List;

import autoimpresor.domain.access.Personas;
import autoimpresor.service.PersonasAccessDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class PersonasAccessService implements PersonasAccessDefinition {

	protected PersonasAccessDefinition biz;
	
	public PersonasAccessService(){

	}
	
	public void setBusinessObject (PersonasAccessDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(Personas p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public Personas get(Long p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(Personas p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(Personas p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(Personas p_domain) throws Exception {
		biz.update(p_domain);
	}
}