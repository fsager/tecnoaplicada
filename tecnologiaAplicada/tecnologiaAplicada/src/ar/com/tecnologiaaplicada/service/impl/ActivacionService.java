package ar.com.tecnologiaaplicada.service.impl;

import java.util.List;

import ar.com.tecnologiaaplicada.domain.Activacion;
import ar.com.tecnologiaaplicada.service.ActivacionDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class ActivacionService implements ActivacionDefinition {

	protected ActivacionDefinition biz;
	
	public ActivacionService(){

	}
	
	public void setBusinessObject (ActivacionDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(Activacion p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public Activacion get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(Activacion p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(Activacion p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(Activacion p_domain) throws Exception {
		biz.update(p_domain);
	}
}