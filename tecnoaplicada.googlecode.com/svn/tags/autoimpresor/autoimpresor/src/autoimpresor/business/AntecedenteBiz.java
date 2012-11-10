package autoimpresor.business;

import java.util.List;

import autoimpresor.domain.Antecedente;
import autoimpresor.persistence.AntecedenteDao;
import autoimpresor.service.AntecedenteDefinition;

public class AntecedenteBiz implements AntecedenteDefinition {
	AntecedenteDao dao;
	
	public void setDao (AntecedenteDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(Antecedente p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public Antecedente get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(Antecedente p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(Antecedente p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(Antecedente p_domain) throws Exception {
		dao.update(p_domain);
	}

}
