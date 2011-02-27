package ar.com.tecnologiaaplicada.business;

import java.util.List;

import ar.com.tecnologiaaplicada.domain.DetalleLicencia;
import ar.com.tecnologiaaplicada.domain.Licencia;
import ar.com.tecnologiaaplicada.persistence.DetalleLicenciaDao;
import ar.com.tecnologiaaplicada.persistence.LicenciaDao;
import ar.com.tecnologiaaplicada.service.DetalleLicenciaDefinition;

public class DetalleLicenciaBiz implements DetalleLicenciaDefinition {
	DetalleLicenciaDao dao;
	
	public void setDao (DetalleLicenciaDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(DetalleLicencia p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public DetalleLicencia get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(DetalleLicencia p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(DetalleLicencia p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(DetalleLicencia p_domain) throws Exception {
		dao.update(p_domain);
	}
}
