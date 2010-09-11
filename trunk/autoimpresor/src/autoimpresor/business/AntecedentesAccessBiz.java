package autoimpresor.business;

import java.util.List;

import autoimpresor.domain.access.Antecedentes;
import autoimpresor.persistence.AntecedentesAccessDao;
import autoimpresor.service.AntecedentesAccessDefinition;

public class AntecedentesAccessBiz implements AntecedentesAccessDefinition {
	AntecedentesAccessDao dao;
	
	public void setDao (AntecedentesAccessDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(Antecedentes p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public Antecedentes get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(Antecedentes p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(Antecedentes p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(Antecedentes p_domain) throws Exception {
		dao.update(p_domain);
	}

}
