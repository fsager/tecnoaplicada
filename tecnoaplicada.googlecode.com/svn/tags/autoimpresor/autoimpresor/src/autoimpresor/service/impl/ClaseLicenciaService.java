package autoimpresor.service.impl;

import java.util.List;

import autoimpresor.domain.ClaseLicencia;
import autoimpresor.service.ClaseLicenciaDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class ClaseLicenciaService implements ClaseLicenciaDefinition {

	protected ClaseLicenciaDefinition biz;
	
	public ClaseLicenciaService(){

	}
	
	public void setBusinessObject (ClaseLicenciaDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(ClaseLicencia p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public ClaseLicencia get(Long p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(ClaseLicencia p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(ClaseLicencia p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(ClaseLicencia p_domain) throws Exception {
		biz.update(p_domain);
	}
}