package testerGeneral.business;

import java.util.List;

import testerGeneral.domain.ResultadoDetalleExamen;
import testerGeneral.persistence.ResultadoDetalleExamenDao;
import testerGeneral.service.ResultadoDetalleExamenDefinition;

public class ResultadoDetalleExamenBiz implements ResultadoDetalleExamenDefinition {
	ResultadoDetalleExamenDao dao;
	
	public void setDao (ResultadoDetalleExamenDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(ResultadoDetalleExamen p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public ResultadoDetalleExamen get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(ResultadoDetalleExamen p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(ResultadoDetalleExamen p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(ResultadoDetalleExamen p_domain) throws Exception {
		dao.update(p_domain);
	}

}
