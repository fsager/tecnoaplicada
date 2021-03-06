package testerGeneral.service.impl;

import java.util.List;

import testerGeneral.domain.ExamenDetalle;
import testerGeneral.service.ExamenDetalleDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class ExamenDetalleService implements ExamenDetalleDefinition {

	protected ExamenDetalleDefinition biz;
	
	public ExamenDetalleService(){

	}
	
	public void setBusinessObject (ExamenDetalleDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(ExamenDetalle p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public ExamenDetalle get(Long p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(ExamenDetalle p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(ExamenDetalle p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(ExamenDetalle p_domain) throws Exception {
		biz.update(p_domain);
	}
}