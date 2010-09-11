package testerGeneral.service.impl;

import java.util.List;

import testerGeneral.domain.UsuarioExamen;
import testerGeneral.service.UsuarioExamenDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class UsuarioExamenService implements UsuarioExamenDefinition {

	protected UsuarioExamenDefinition biz;
	
	public UsuarioExamenService(){

	}
	
	public void setBusinessObject (UsuarioExamenDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(UsuarioExamen p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public UsuarioExamen get(Long p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(UsuarioExamen p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(UsuarioExamen p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(UsuarioExamen p_domain) throws Exception {
		biz.update(p_domain);
	}
}