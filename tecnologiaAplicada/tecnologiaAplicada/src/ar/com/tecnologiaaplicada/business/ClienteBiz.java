package ar.com.tecnologiaaplicada.business;

import java.util.List;

import ar.com.tecnologiaaplicada.domain.Cliente;
import ar.com.tecnologiaaplicada.persistence.ClienteDao;
import ar.com.tecnologiaaplicada.service.ClienteDefinition;

public class ClienteBiz implements ClienteDefinition {
	ClienteDao dao;
	
	public void setDao (ClienteDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(Cliente p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public Cliente get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(Cliente p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(Cliente p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(Cliente p_domain) throws Exception {
		dao.update(p_domain);
	}

}
