package testerGeneral.service.impl;

import java.util.List;

import testerGeneral.domain.Resultado;
import testerGeneral.service.ResultadoDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class ResultadoService implements ResultadoDefinition {

	protected ResultadoDefinition biz;
	
	public ResultadoService(){

	}
	
	public void setBusinessObject (ResultadoDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(Resultado p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public Resultado get(Long p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(Resultado p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(Resultado p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(Resultado p_domain) throws Exception {
		biz.update(p_domain);
	}
}