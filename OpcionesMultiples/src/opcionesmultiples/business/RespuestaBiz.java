package opcionesmultiples.business;

import java.util.List;

import opcionesmultiples.domain.Respuesta;
import opcionesmultiples.persistence.RespuestaDao;
import opcionesmultiples.service.RespuestaDefinition;

public class RespuestaBiz implements RespuestaDefinition {
	RespuestaDao dao;
	
	public void setDao (RespuestaDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(Respuesta p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public Respuesta get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(Respuesta p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(Respuesta p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(Respuesta p_domain) throws Exception {
		dao.update(p_domain);
	}

}
