package testerGeneral.business;

import java.util.List;

import testerGeneral.domain.PersonaExamen;
import testerGeneral.persistence.PersonaExamenDao;
import testerGeneral.service.PersonaExamenDefinition;

public class PersonaExamenBiz implements PersonaExamenDefinition {
	PersonaExamenDao dao;
	
	public void setDao (PersonaExamenDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(PersonaExamen p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public PersonaExamen get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(PersonaExamen p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(PersonaExamen p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(PersonaExamen p_domain) throws Exception {
		dao.update(p_domain);
	}

}
