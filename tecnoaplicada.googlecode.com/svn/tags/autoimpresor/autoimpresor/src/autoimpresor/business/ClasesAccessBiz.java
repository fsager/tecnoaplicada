package autoimpresor.business;

import java.util.List;

import autoimpresor.domain.access.Clases;
import autoimpresor.persistence.ClasesAccessDao;
import autoimpresor.service.ClasesAccessDefinition;

public class ClasesAccessBiz implements ClasesAccessDefinition {
	ClasesAccessDao dao;
	
	public void setDao (ClasesAccessDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(Clases p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public Clases get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(Clases p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(Clases p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(Clases p_domain) throws Exception {
		dao.update(p_domain);
	}

}
