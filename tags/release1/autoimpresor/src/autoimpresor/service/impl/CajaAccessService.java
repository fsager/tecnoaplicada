package autoimpresor.service.impl;

import java.util.List;

import autoimpresor.domain.access.Caja;
import autoimpresor.service.CajaAccessDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class CajaAccessService implements CajaAccessDefinition {

	protected CajaAccessDefinition biz;
	
	public CajaAccessService(){

	}
	
	public void setBusinessObject (CajaAccessDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(Caja p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public Caja get(Long p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(Caja p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(Caja p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(Caja p_domain) throws Exception {
		biz.update(p_domain);
	}
}