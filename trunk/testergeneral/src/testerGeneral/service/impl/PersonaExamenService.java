package testerGeneral.service.impl;

import java.util.Date;
import java.util.List;

import testerGeneral.domain.PersonaExamen;
import testerGeneral.service.PersonaExamenDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class PersonaExamenService implements PersonaExamenDefinition {

	protected PersonaExamenDefinition biz;
	
	public PersonaExamenService(){

	}
	
	public void setBusinessObject (PersonaExamenDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(PersonaExamen p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public PersonaExamen get(Long p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(PersonaExamen p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(PersonaExamen p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(PersonaExamen p_domain) throws Exception {
		biz.update(p_domain);
	}
	
	public Long getCantidadExamenes(Date lastDate) throws Exception {
		return biz.getCantidadExamenes(lastDate);
	}
}