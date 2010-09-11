package testerGeneral.business;

import java.util.Date;
import java.util.List;

import testerGeneral.domain.Auditoria;
import testerGeneral.domain.Persona;
import testerGeneral.persistence.PersonaDao;
import testerGeneral.service.PersonaDefinition;

public class PersonaBiz implements PersonaDefinition {
	PersonaDao dao;
	
	public void setDao (PersonaDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(Persona p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public Persona get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(Persona p_example) throws Exception {
		return dao.getAll(p_example);
	}
	
    public List getAll(Persona p_example,Date desde,Date hasta) throws Exception {
    	return dao.getAll(p_example, desde, hasta);
    }

	public void insert(Persona p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(Persona p_domain) throws Exception {
		dao.update(p_domain);
	}

}
