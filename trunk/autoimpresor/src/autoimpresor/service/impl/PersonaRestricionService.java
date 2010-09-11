package autoimpresor.service.impl;

import java.util.List;

import testerGeneral.domain.PersonaRestricion;
import testerGeneral.service.PersonaRestricionDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class PersonaRestricionService implements PersonaRestricionDefinition {

	protected PersonaRestricionDefinition biz;
	
	public PersonaRestricionService(){

	}
	
	public void setBusinessObject (PersonaRestricionDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(PersonaRestricion p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public PersonaRestricion get(Long p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(PersonaRestricion p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(PersonaRestricion p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(PersonaRestricion p_domain) throws Exception {
		biz.update(p_domain);
	}
}