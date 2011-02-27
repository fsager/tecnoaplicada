package ar.com.tecnologiaaplicada.business;

import java.util.List;

import ar.com.tecnologiaaplicada.domain.Activacion;
import ar.com.tecnologiaaplicada.persistence.ActivacionDao;
import ar.com.tecnologiaaplicada.service.ActivacionDefinition;

public class ActivacionBiz implements ActivacionDefinition {
	ActivacionDao dao;
	
	public void setDao (ActivacionDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(Activacion p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public Activacion get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(Activacion p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(Activacion p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(Activacion p_domain) throws Exception {
		dao.update(p_domain);
	}

}
