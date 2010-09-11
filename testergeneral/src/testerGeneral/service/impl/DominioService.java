package testerGeneral.service.impl;

import java.util.List;

import testerGeneral.domain.Dominio;
import testerGeneral.service.DominioDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class DominioService implements DominioDefinition {

	protected DominioDefinition biz;
	
	public DominioService(){

	}
	
	public void setBusinessObject (DominioDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(Dominio p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public Dominio get(Long p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(Dominio p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(Dominio p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(Dominio p_domain) throws Exception {
		biz.update(p_domain);
	}
}