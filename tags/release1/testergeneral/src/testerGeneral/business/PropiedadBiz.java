package testerGeneral.business;

import java.util.List;

import testerGeneral.domain.Propiedad;
import testerGeneral.persistence.PropiedadDao;
import testerGeneral.service.PropiedadDefinition;

public class PropiedadBiz implements PropiedadDefinition {
	PropiedadDao dao;
	
	public void setDao (PropiedadDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(Propiedad p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public Propiedad get(String p_Id) throws Exception {
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
