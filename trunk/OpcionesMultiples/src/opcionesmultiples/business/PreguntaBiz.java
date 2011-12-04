package opcionesmultiples.business;

import java.util.List;

import opcionesmultiples.domain.Pregunta;
import opcionesmultiples.persistence.PreguntaDao;
import opcionesmultiples.service.PreguntaDefinition;

public class PreguntaBiz implements PreguntaDefinition {
	PreguntaDao dao;
	
	public void setDao (PreguntaDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(Pregunta p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public Pregunta get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(Pregunta p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(Pregunta p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(Pregunta p_domain) throws Exception {
		dao.update(p_domain);
	}

}
