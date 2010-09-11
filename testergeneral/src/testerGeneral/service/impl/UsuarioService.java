package testerGeneral.service.impl;

import java.io.Serializable;
import java.util.List;

import testerGeneral.domain.UsuarioCommon;
import testerGeneral.service.UsuarioDefinition;

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

	public void delete(UsuarioCommon p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public UsuarioCommon getUsrName(String p_Id,Class classs) throws Exception {
		return biz.getUsrName(p_Id,classs);
	}
	
	public UsuarioCommon get(Serializable p_Id,Class classs) throws Exception {
		return biz.get(p_Id,classs);
	}

	
	public List getAll(UsuarioCommon p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(UsuarioCommon p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(UsuarioCommon p_domain) throws Exception {
		biz.update(p_domain);
	}
}