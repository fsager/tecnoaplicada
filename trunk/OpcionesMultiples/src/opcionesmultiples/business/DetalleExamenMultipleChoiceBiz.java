package opcionesmultiples.business;

import java.util.List;

import opcionesmultiples.domain.DetalleExamenMultipleChoice;
import opcionesmultiples.persistence.DetalleExamenMultipleChoiceDao;
import opcionesmultiples.service.DetalleExamenMultipleChoiceDefinition;

public class DetalleExamenMultipleChoiceBiz implements DetalleExamenMultipleChoiceDefinition {
	DetalleExamenMultipleChoiceDao dao;
	
	public void setDao (DetalleExamenMultipleChoiceDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(DetalleExamenMultipleChoice p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public DetalleExamenMultipleChoice get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(DetalleExamenMultipleChoice p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(DetalleExamenMultipleChoice p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(DetalleExamenMultipleChoice p_domain) throws Exception {
		dao.update(p_domain);
	}

}
