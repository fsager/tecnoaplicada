package autoimpresor.service.impl;

import java.util.List;

import autoimpresor.domain.access.Localidades;
import autoimpresor.service.LocalidadesAccessDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class LocalidadesAccessService implements LocalidadesAccessDefinition {

	protected LocalidadesAccessDefinition biz;
	
	public LocalidadesAccessService(){

	}
	
	public void setBusinessObject (LocalidadesAccessDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(Localidades p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public Localidades get(Long p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(Localidades p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(Localidades p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(Localidades p_domain) throws Exception {
		biz.update(p_domain);
	}
}