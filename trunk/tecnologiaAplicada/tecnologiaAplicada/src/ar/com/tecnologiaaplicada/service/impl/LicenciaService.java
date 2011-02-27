package ar.com.tecnologiaaplicada.service.impl;

import java.util.List;

import ar.com.tecnologiaaplicada.domain.Licencia;
import ar.com.tecnologiaaplicada.service.LicenciaDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class LicenciaService implements LicenciaDefinition {

	protected LicenciaDefinition biz;
	
	public LicenciaService(){

	}
	
	public void setBusinessObject (LicenciaDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(Licencia p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public Licencia get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(Licencia p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(Licencia p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(Licencia p_domain) throws Exception {
		biz.update(p_domain);
	}
}