package autoimpresor.business;

import java.util.List;

import autoimpresor.domain.access.Caja;
import autoimpresor.persistence.CajaAccessDao;
import autoimpresor.service.CajaAccessDefinition;

public class CajaAccessBiz implements CajaAccessDefinition {
	CajaAccessDao dao;
	
	public void setDao (CajaAccessDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(Caja p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public Caja get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(Caja p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(Caja p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(Caja p_domain) throws Exception {
		dao.update(p_domain);
	}

}
