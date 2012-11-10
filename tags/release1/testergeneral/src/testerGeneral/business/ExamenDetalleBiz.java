package testerGeneral.business;

import java.util.List;

import testerGeneral.domain.ExamenDetalle;
import testerGeneral.persistence.ExamenDetalleDao;
import testerGeneral.service.ExamenDetalleDefinition;

public class ExamenDetalleBiz implements ExamenDetalleDefinition {
	ExamenDetalleDao dao;
	
	public void setDao (ExamenDetalleDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(ExamenDetalle p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public ExamenDetalle get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(ExamenDetalle p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(ExamenDetalle p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(ExamenDetalle p_domain) throws Exception {
		dao.update(p_domain);
	}

}
