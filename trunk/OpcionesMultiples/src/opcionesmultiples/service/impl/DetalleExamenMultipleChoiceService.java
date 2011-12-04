package opcionesmultiples.service.impl;

import java.util.List;

import opcionesmultiples.domain.DetalleExamenMultipleChoice;
import opcionesmultiples.service.DetalleExamenMultipleChoiceDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class DetalleExamenMultipleChoiceService implements DetalleExamenMultipleChoiceDefinition {

	protected DetalleExamenMultipleChoiceDefinition biz;
	
	public DetalleExamenMultipleChoiceService(){

	}
	
	public void setBusinessObject (DetalleExamenMultipleChoiceDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(DetalleExamenMultipleChoice p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public DetalleExamenMultipleChoice get(Long p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(DetalleExamenMultipleChoice p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(DetalleExamenMultipleChoice p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(DetalleExamenMultipleChoice p_domain) throws Exception {
		biz.update(p_domain);
	}
}