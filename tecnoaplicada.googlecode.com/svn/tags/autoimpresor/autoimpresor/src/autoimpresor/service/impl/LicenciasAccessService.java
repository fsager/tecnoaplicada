package autoimpresor.service.impl;

import java.util.List;

import autoimpresor.domain.access.Licencias;
import autoimpresor.service.LicenciasAccessDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class LicenciasAccessService implements LicenciasAccessDefinition {

	protected LicenciasAccessDefinition biz;
	
	public LicenciasAccessService(){

	}
	
	public void setBusinessObject (LicenciasAccessDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(Licencias p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public Licencias get(Long p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(Licencias p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(Licencias p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(Licencias p_domain) throws Exception {
		biz.update(p_domain);
	}
}