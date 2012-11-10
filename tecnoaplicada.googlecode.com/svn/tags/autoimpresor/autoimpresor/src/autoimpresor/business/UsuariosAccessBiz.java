package autoimpresor.business;

import java.util.List;

import autoimpresor.domain.access.Usuarios;
import autoimpresor.persistence.UsuariosAccessDao;
import autoimpresor.service.UsuariosAccessDefinition;

public class UsuariosAccessBiz implements UsuariosAccessDefinition {
	UsuariosAccessDao dao;
	
	public void setDao (UsuariosAccessDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(Usuarios p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public Usuarios get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(Usuarios p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(Usuarios p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(Usuarios p_domain) throws Exception {
		dao.update(p_domain);
	}

}
