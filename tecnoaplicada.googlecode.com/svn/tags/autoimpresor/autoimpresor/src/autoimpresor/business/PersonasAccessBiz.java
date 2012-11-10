package autoimpresor.business;

import java.util.List;

import autoimpresor.domain.access.Personas;
import autoimpresor.persistence.PersonasAccessDao;
import autoimpresor.service.PersonasAccessDefinition;

public class PersonasAccessBiz implements PersonasAccessDefinition {
	PersonasAccessDao dao;
	
	public void setDao (PersonasAccessDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(Personas p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public Personas get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(Personas p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(Personas p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(Personas p_domain) throws Exception {
		dao.update(p_domain);
	}

}
