package opcionesmultiples.service.impl;

import java.util.List;

import opcionesmultiples.domain.Pregunta;
import opcionesmultiples.service.PreguntaDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class PreguntaService implements PreguntaDefinition {

	protected PreguntaDefinition biz;
	
	public PreguntaService(){

	}
	
	public void setBusinessObject (PreguntaDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(Pregunta p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public Pregunta get(Long p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(Pregunta p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(Pregunta p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(Pregunta p_domain) throws Exception {
		biz.update(p_domain);
	}
}