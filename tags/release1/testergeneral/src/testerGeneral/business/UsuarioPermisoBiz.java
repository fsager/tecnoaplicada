package testerGeneral.business;

import java.util.List;

import testerGeneral.domain.UsuarioPermiso;
import testerGeneral.persistence.UsuarioPermisoDao;
import testerGeneral.service.UsuarioPermisoDefinition;

public class UsuarioPermisoBiz implements UsuarioPermisoDefinition {
	UsuarioPermisoDao dao;
	
	public void setDao (UsuarioPermisoDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(UsuarioPermiso p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public UsuarioPermiso get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(UsuarioPermiso p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(UsuarioPermiso p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(UsuarioPermiso p_domain) throws Exception {
		dao.update(p_domain);
	}

}
