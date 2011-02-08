package testerGeneral.business;

import java.util.Date;
import java.util.List;

import testerGeneral.domain.Auditoria;
import testerGeneral.persistence.AuditoriaDao;
import testerGeneral.service.AuditoriaDefinition;

public class AuditoriaBiz implements AuditoriaDefinition {
	AuditoriaDao dao;
	
	public void setDao (AuditoriaDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(Auditoria p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public Auditoria get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(Auditoria p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(Auditoria p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(Auditoria p_domain) throws Exception {
		dao.update(p_domain);
	}
	
    public List getAll(Auditoria p_example,Date desde,Date hasta) throws Exception {
    	return dao.getAll(p_example, desde, hasta);
    }
    
    public int deleteAll(Auditoria p_example) throws Exception {
    	return dao.deleteAll(p_example);
    }

}
