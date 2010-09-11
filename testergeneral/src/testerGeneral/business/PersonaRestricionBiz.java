package testerGeneral.business;

import java.util.List;

import testerGeneral.domain.PersonaRestricion;
import testerGeneral.persistence.PersonaRestricionDao;
import testerGeneral.service.PersonaRestricionDefinition;

public class PersonaRestricionBiz implements PersonaRestricionDefinition {
	PersonaRestricionDao dao;
	
	public void setDao (PersonaRestricionDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(PersonaRestricion p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public PersonaRestricion get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(PersonaRestricion p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(PersonaRestricion p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(PersonaRestricion p_domain) throws Exception {
		dao.update(p_domain);
	}

}
