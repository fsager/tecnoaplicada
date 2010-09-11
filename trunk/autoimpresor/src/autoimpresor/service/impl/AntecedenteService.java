package autoimpresor.service.impl;

import java.util.List;

import autoimpresor.domain.Antecedente;
import autoimpresor.service.AntecedenteDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class AntecedenteService implements AntecedenteDefinition {

	protected AntecedenteDefinition biz;
	
	public AntecedenteService(){

	}
	
	public void setBusinessObject (AntecedenteDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(Antecedente p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public Antecedente get(Long p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(Antecedente p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(Antecedente p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(Antecedente p_domain) throws Exception {
		biz.update(p_domain);
	}
}