package autoimpresor.service.impl;

import java.util.List;

import autoimpresor.domain.access.Exportados;
import autoimpresor.service.ExportadosAccessDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class ExportadosAccessService implements ExportadosAccessDefinition {

	protected ExportadosAccessDefinition biz;
	
	public ExportadosAccessService(){

	}
	
	public void setBusinessObject (ExportadosAccessDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(Exportados p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public Exportados get(Long p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(Exportados p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(Exportados p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(Exportados p_domain) throws Exception {
		biz.update(p_domain);
	}
}