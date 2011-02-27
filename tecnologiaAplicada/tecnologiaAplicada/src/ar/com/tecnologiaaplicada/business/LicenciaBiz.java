package ar.com.tecnologiaaplicada.business;

import java.util.List;

import ar.com.tecnologiaaplicada.domain.Licencia;
import ar.com.tecnologiaaplicada.persistence.LicenciaDao;
import ar.com.tecnologiaaplicada.service.LicenciaDefinition;

public class LicenciaBiz implements LicenciaDefinition {
	LicenciaDao dao;
	
	public void setDao (LicenciaDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(Licencia p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public Licencia get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(Licencia p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(Licencia p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(Licencia p_domain) throws Exception {
		dao.update(p_domain);
	}

}
