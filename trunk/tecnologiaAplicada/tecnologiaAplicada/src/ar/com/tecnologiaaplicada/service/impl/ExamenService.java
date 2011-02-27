package ar.com.tecnologiaaplicada.service.impl;

import java.util.List;

import ar.com.tecnologiaaplicada.domain.Examen;
import ar.com.tecnologiaaplicada.service.ExamenDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class ExamenService implements ExamenDefinition {

	protected ExamenDefinition biz;
	
	public ExamenService(){

	}
	
	public void setBusinessObject (ExamenDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(Examen p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public Examen get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(Examen p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(Examen p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(Examen p_domain) throws Exception {
		biz.update(p_domain);
	}
}