package ar.com.tecnologiaaplicada.business;

import java.util.List;

import ar.com.tecnologiaaplicada.domain.Usuario;
import ar.com.tecnologiaaplicada.persistence.UsuarioDao;
import ar.com.tecnologiaaplicada.service.UsuarioDefinition;

public class UsuarioBiz implements UsuarioDefinition {
	UsuarioDao dao;
	
	public void setDao (UsuarioDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(Usuario p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public Usuario get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(Usuario p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(Usuario p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(Usuario p_domain) throws Exception {
		dao.update(p_domain);
	}

}
