package testerGeneral.business;

import java.util.List;

import frontend.utils.Util;

import testerGeneral.domain.Constantes;
import testerGeneral.domain.Dominio;
import testerGeneral.domain.Persona;
import testerGeneral.persistence.DominioDao;
import testerGeneral.service.DominioDefinition;

public class DominioBiz implements DominioDefinition {
	DominioDao dao;
	
	public void setDao (DominioDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(Dominio p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public Dominio get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(Dominio p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(Dominio p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(Dominio p_domain) throws Exception {
		dao.update(p_domain);
	}
}
