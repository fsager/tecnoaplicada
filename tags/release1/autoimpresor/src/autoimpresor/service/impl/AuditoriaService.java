package autoimpresor.service.impl;

import java.util.Date;
import java.util.List;

import testerGeneral.domain.Auditoria;
import testerGeneral.service.AuditoriaDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class AuditoriaService implements AuditoriaDefinition {

	protected AuditoriaDefinition biz;
	
	public AuditoriaService(){

	}
	
	public void setBusinessObject (AuditoriaDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(Auditoria p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public Auditoria get(Long p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(Auditoria p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(Auditoria p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(Auditoria p_domain) throws Exception {
		biz.update(p_domain);
	}
	
	public List getAll(Auditoria p_example,Date desde,Date hasta) throws Exception {
    	return biz.getAll(p_example, desde, hasta);
    }
    
    public int deleteAll(Auditoria p_example) throws Exception {
    	return biz.deleteAll(p_example);
    }
}