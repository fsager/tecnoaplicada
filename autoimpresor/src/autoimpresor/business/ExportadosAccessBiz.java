package autoimpresor.business;

import java.util.List;

import autoimpresor.domain.access.Exportados;
import autoimpresor.persistence.ExportadosAccessDao;
import autoimpresor.service.ExportadosAccessDefinition;

public class ExportadosAccessBiz implements ExportadosAccessDefinition {
	ExportadosAccessDao dao;
	
	public void setDao (ExportadosAccessDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(Exportados p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public Exportados get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(Exportados p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(Exportados p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(Exportados p_domain) throws Exception {
		dao.update(p_domain);
	}

}
