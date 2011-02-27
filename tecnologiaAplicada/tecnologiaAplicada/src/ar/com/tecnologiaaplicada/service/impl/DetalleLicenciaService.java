package ar.com.tecnologiaaplicada.service.impl;

import java.util.List;

import ar.com.tecnologiaaplicada.domain.DetalleLicencia;
import ar.com.tecnologiaaplicada.service.DetalleLicenciaDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class DetalleLicenciaService implements DetalleLicenciaDefinition {

	protected DetalleLicenciaDefinition biz;
	
	public DetalleLicenciaService(){

	}
	
	public void setBusinessObject (DetalleLicenciaDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(DetalleLicencia p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public DetalleLicencia get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(DetalleLicencia p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(DetalleLicencia p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(DetalleLicencia p_domain) throws Exception {
		biz.update(p_domain);
	}
}