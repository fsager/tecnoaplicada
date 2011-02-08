package testerGeneral.service.impl;

import java.util.List;

import testerGeneral.domain.ResultadoDetalleExamen;
import testerGeneral.service.ResultadoDetalleExamenDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class ResultadoDetalleExamenService implements ResultadoDetalleExamenDefinition {

	protected ResultadoDetalleExamenDefinition biz;
	
	public ResultadoDetalleExamenService(){

	}
	
	public void setBusinessObject (ResultadoDetalleExamenDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(ResultadoDetalleExamen p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public ResultadoDetalleExamen get(Long p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(ResultadoDetalleExamen p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(ResultadoDetalleExamen p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(ResultadoDetalleExamen p_domain) throws Exception {
		biz.update(p_domain);
	}
}