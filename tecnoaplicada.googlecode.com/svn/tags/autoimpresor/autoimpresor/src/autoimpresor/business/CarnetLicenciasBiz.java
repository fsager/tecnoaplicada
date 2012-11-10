package autoimpresor.business;

import java.util.Date;
import java.util.List;

import autoimpresor.domain.CarnetLicencias;
import autoimpresor.persistence.CarnetLicenciasDao;
import autoimpresor.service.CarnetLicenciasDefinition;

public class CarnetLicenciasBiz implements CarnetLicenciasDefinition {
	CarnetLicenciasDao dao;
	
	public void setDao (CarnetLicenciasDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(CarnetLicencias p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public CarnetLicencias get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(CarnetLicencias p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(CarnetLicencias p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(CarnetLicencias p_domain) throws Exception {
		dao.update(p_domain);
	}
	
    public List getAll(CarnetLicencias p_example,Date desde, Date hasta) throws Exception {
    	return dao.getAll(p_example,desde,hasta);
    }
    
    public List getPendientes() throws Exception {
    	return dao.getPendientes();
    }
}
