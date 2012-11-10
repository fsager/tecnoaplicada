package autoimpresor.service.impl;

import java.util.List;

import autoimpresor.domain.access.Usuarios;
import autoimpresor.service.UsuariosAccessDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class UsuariosAccessService implements UsuariosAccessDefinition {

	protected UsuariosAccessDefinition biz;
	
	public UsuariosAccessService(){

	}
	
	public void setBusinessObject (UsuariosAccessDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(Usuarios p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public Usuarios get(Long p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(Usuarios p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(Usuarios p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(Usuarios p_domain) throws Exception {
		biz.update(p_domain);
	}
}