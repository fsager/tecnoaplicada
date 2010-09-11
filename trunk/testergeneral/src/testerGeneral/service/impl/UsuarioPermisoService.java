package testerGeneral.service.impl;

import java.util.List;

import testerGeneral.domain.UsuarioPermiso;
import testerGeneral.service.UsuarioPermisoDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class UsuarioPermisoService implements UsuarioPermisoDefinition {

	protected UsuarioPermisoDefinition biz;
	
	public UsuarioPermisoService(){

	}
	
	public void setBusinessObject (UsuarioPermisoDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(UsuarioPermiso p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public UsuarioPermiso get(Long p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(UsuarioPermiso p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(UsuarioPermiso p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(UsuarioPermiso p_domain) throws Exception {
		biz.update(p_domain);
	}
}