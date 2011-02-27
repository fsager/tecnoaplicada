package ar.com.tecnologiaaplicada.business;

import java.util.List;

import ar.com.tecnologiaaplicada.domain.Propiedad;
import ar.com.tecnologiaaplicada.persistence.PropiedadDao;
import ar.com.tecnologiaaplicada.service.PropiedadDefinition;

public class PropiedadBiz implements PropiedadDefinition {
	PropiedadDao dao;
	
	public void setDao (PropiedadDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(Propiedad p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public Propiedad get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(Propiedad p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(Propiedad p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(Propiedad p_domain) throws Exception {
		dao.update(p_domain);
	}

}
