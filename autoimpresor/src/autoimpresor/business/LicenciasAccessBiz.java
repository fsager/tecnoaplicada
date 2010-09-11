package autoimpresor.business;

import java.util.List;

import autoimpresor.domain.access.Licencias;
import autoimpresor.persistence.LicenciasAccessDao;
import autoimpresor.service.LicenciasAccessDefinition;

public class LicenciasAccessBiz implements LicenciasAccessDefinition {
	LicenciasAccessDao dao;
	
	public void setDao (LicenciasAccessDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(Licencias p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public Licencias get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(Licencias p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(Licencias p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(Licencias p_domain) throws Exception {
		dao.update(p_domain);
	}

}
