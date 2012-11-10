package testerGeneral.business;

import java.util.List;

import testerGeneral.domain.UsuarioExamen;
import testerGeneral.persistence.UsuarioExamenDao;
import testerGeneral.service.UsuarioExamenDefinition;

public class UsuarioExamenBiz implements UsuarioExamenDefinition {
	UsuarioExamenDao dao;
	
	public void setDao (UsuarioExamenDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(UsuarioExamen p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public UsuarioExamen get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(UsuarioExamen p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(UsuarioExamen p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(UsuarioExamen p_domain) throws Exception {
		dao.update(p_domain);
	}

}
