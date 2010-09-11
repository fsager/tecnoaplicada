package autoimpresor.business;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import testerGeneral.domain.Propiedad;
import testerGeneral.service.PropiedadDefinition;

import autoimpresor.domain.Antecedente;
import autoimpresor.domain.ClaseLicencia;
import autoimpresor.domain.Licencia;
import autoimpresor.domain.Persona;
import autoimpresor.domain.access.Antecedentes;
import autoimpresor.domain.access.Clases;
import autoimpresor.domain.access.Personas;
import autoimpresor.persistence.LicenciaDao;
import autoimpresor.service.AntecedenteDefinition;
import autoimpresor.service.AntecedentesAccessDefinition;
import autoimpresor.service.CajaAccessDefinition;
import autoimpresor.service.CajaDefinition;
import autoimpresor.service.ClaseLicenciaDefinition;
import autoimpresor.service.ClasesAccessDefinition;
import autoimpresor.service.LicenciaDefinition;

public class LicenciaBiz implements LicenciaDefinition {
	LicenciaDao dao;
	
	public void setDao (LicenciaDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(Licencia p_domain) throws Exception {
		dao.delete(p_domain);		
	}

	public Licencia get(Long p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(Licencia p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public List getAll(Licencia p_example,Date desde, Date hasta) throws Exception {
		return dao.getAll(p_example,desde,hasta);
	}
	
	public void insert(Licencia p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(Licencia p_domain) throws Exception {
		dao.update(p_domain);
	}
	
	
	public Long getMaxNumeroLicencia() throws Exception {
		return dao.getMaxNumeroLicencia();
	}
}
