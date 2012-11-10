package autoimpresor.service.impl;

import java.util.List;

import autoimpresor.domain.access.Clases;
import autoimpresor.service.ClasesAccessDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class ClasesAccessService implements ClasesAccessDefinition {

	protected ClasesAccessDefinition biz;
	
	public ClasesAccessService(){

	}
	
	public void setBusinessObject (ClasesAccessDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(Clases p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public Clases get(Long p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(Clases p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(Clases p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(Clases p_domain) throws Exception {
		biz.update(p_domain);
	}
}