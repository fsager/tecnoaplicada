package autoimpresor.service.impl;

import java.util.List;

import autoimpresor.domain.access.Antecedentes;
import autoimpresor.service.AntecedentesAccessDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class AntecedentesAccessService implements AntecedentesAccessDefinition {

	protected AntecedentesAccessDefinition biz;
	
	public AntecedentesAccessService(){

	}
	
	public void setBusinessObject (AntecedentesAccessDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(Antecedentes p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public Antecedentes get(Long p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(Antecedentes p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(Antecedentes p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(Antecedentes p_domain) throws Exception {
		biz.update(p_domain);
	}
}