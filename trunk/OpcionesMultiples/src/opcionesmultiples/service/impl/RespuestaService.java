package opcionesmultiples.service.impl;

import java.util.List;

import opcionesmultiples.domain.Respuesta;
import opcionesmultiples.service.RespuestaDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class RespuestaService implements RespuestaDefinition {

	protected RespuestaDefinition biz;
	
	public RespuestaService(){

	}
	
	public void setBusinessObject (RespuestaDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(Respuesta p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public Respuesta get(Long p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(Respuesta p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(Respuesta p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(Respuesta p_domain) throws Exception {
		biz.update(p_domain);
	}
}