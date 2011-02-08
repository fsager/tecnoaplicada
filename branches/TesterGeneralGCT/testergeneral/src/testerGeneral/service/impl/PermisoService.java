package testerGeneral.service.impl;

import java.util.List;

import testerGeneral.domain.Permiso;
import testerGeneral.service.PermisoDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class PermisoService implements PermisoDefinition {

	protected PermisoDefinition biz;
	
	public PermisoService(){

	}
	
	public void setBusinessObject (PermisoDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(Permiso p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public Permiso get(Long p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(Permiso p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(Permiso p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(Permiso p_domain) throws Exception {
		biz.update(p_domain);
	}
}