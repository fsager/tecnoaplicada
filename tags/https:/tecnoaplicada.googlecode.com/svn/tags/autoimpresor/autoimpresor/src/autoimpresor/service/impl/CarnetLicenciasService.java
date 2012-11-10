package autoimpresor.service.impl;

import java.util.Date;
import java.util.List;

import autoimpresor.domain.CarnetLicencias;
import autoimpresor.service.CarnetLicenciasDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class CarnetLicenciasService implements CarnetLicenciasDefinition {

	protected CarnetLicenciasDefinition biz;
	
	public CarnetLicenciasService(){

	}
	
	public void setBusinessObject (CarnetLicenciasDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(CarnetLicencias p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public CarnetLicencias get(Long p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(CarnetLicencias p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(CarnetLicencias p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(CarnetLicencias p_domain) throws Exception {
		biz.update(p_domain);
	}
	
    public List getAll(CarnetLicencias p_example,Date desde, Date hasta) throws Exception {
    	return biz.getAll(p_example,desde,hasta);
    }
    
    public List getPendientes() throws Exception {
    	return biz.getPendientes();
    }
}