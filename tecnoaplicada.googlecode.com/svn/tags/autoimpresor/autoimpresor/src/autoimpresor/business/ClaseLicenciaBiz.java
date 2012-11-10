package autoimpresor.business;

import java.util.List;

import autoimpresor.domain.ClaseLicencia;
import autoimpresor.persistence.ClaseLicenciaDao;
import autoimpresor.service.ClaseLicenciaDefinition;

public class ClaseLicenciaBiz implements ClaseLicenciaDefinition {
	ClaseLicenciaDao dao;
	
	public void setDao (ClaseLicenciaDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(ClaseLicencia p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public ClaseLicencia get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(ClaseLicencia p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(ClaseLicencia p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(ClaseLicencia p_domain) throws Exception {
		dao.update(p_domain);
	}

}
