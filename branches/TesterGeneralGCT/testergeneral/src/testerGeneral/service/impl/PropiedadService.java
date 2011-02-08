package testerGeneral.service.impl;

import java.util.List;

import testerGeneral.domain.Propiedad;
import testerGeneral.service.PropiedadDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class PropiedadService implements PropiedadDefinition {

	protected PropiedadDefinition biz;
	
	public PropiedadService(){

	}
	
	public void setBusinessObject (PropiedadDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(Propiedad p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public Propiedad get(String p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(Propiedad p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(Propiedad p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(Propiedad p_domain) throws Exception {
		biz.update(p_domain);
	}
}