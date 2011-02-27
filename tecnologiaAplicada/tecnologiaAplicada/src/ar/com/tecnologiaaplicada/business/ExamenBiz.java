package ar.com.tecnologiaaplicada.business;

import java.util.List;

import ar.com.tecnologiaaplicada.domain.Examen;
import ar.com.tecnologiaaplicada.persistence.ExamenDao;
import ar.com.tecnologiaaplicada.service.ExamenDefinition;

public class ExamenBiz implements ExamenDefinition {
	ExamenDao dao;
	
	public void setDao (ExamenDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(Examen p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public Examen get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(Examen p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(Examen p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(Examen p_domain) throws Exception {
		dao.update(p_domain);
	}

}
