package ar.com.tecnologiaaplicada.service.impl;

import java.util.List;

import ar.com.tecnologiaaplicada.domain.Usuario;
import ar.com.tecnologiaaplicada.service.UsuarioDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class UsuarioService implements UsuarioDefinition {

	protected UsuarioDefinition biz;
	
	public UsuarioService(){

	}
	
	public void setBusinessObject (UsuarioDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(Usuario p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public Usuario get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(Usuario p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(Usuario p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(Usuario p_domain) throws Exception {
		biz.update(p_domain);
	}
}