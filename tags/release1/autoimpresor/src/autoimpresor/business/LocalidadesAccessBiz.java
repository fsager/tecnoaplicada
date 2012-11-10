package autoimpresor.business;

import java.util.List;

import autoimpresor.domain.access.Localidades;
import autoimpresor.persistence.LocalidadesAccessDao;
import autoimpresor.service.LocalidadesAccessDefinition;

public class LocalidadesAccessBiz implements LocalidadesAccessDefinition {
	LocalidadesAccessDao dao;
	
	public void setDao (LocalidadesAccessDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(Localidades p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public Localidades get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(Localidades p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(Localidades p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(Localidades p_domain) throws Exception {
		dao.update(p_domain);
	}

}
