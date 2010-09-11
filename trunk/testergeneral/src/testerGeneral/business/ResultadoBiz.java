package testerGeneral.business;

import java.util.List;

import testerGeneral.domain.Resultado;
import testerGeneral.persistence.ResultadoDao;
import testerGeneral.service.ResultadoDefinition;

public class ResultadoBiz implements ResultadoDefinition {
	ResultadoDao dao;
	
	public void setDao (ResultadoDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(Resultado p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public Resultado get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(Resultado p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(Resultado p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(Resultado p_domain) throws Exception {
		dao.update(p_domain);
	}

}
