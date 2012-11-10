package testerGeneral.business;

import java.util.List;

import testerGeneral.domain.Permiso;
import testerGeneral.persistence.PermisoDao;
import testerGeneral.service.PermisoDefinition;

public class PermisoBiz implements PermisoDefinition {
	PermisoDao dao;
	
	public void setDao (PermisoDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(Permiso p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public Permiso get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(Permiso p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(Permiso p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(Permiso p_domain) throws Exception {
		dao.update(p_domain);
	}

}
