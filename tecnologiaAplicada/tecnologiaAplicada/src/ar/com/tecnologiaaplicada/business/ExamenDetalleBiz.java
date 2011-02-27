package ar.com.tecnologiaaplicada.business;

import java.util.ArrayList;
import java.util.List;

import ar.com.tecnologiaaplicada.domain.DetalleLicencia;
import ar.com.tecnologiaaplicada.domain.ExamenDetalle;
import ar.com.tecnologiaaplicada.domain.Licencia;
import ar.com.tecnologiaaplicada.persistence.DetalleLicenciaDao;
import ar.com.tecnologiaaplicada.persistence.ExamenDetalleDao;
import ar.com.tecnologiaaplicada.persistence.LicenciaDao;
import ar.com.tecnologiaaplicada.service.ExamenDetalleDefinition;

public class ExamenDetalleBiz implements ExamenDetalleDefinition {
	ExamenDetalleDao dao;
	LicenciaDao licenciaDao;
	DetalleLicenciaDao detalleLicenciaDao;
	
	public void setDao (ExamenDetalleDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(ExamenDetalle p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public ExamenDetalle get(java.io.Serializable p_Id) throws Exception {
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
	
	public void setLicenciaDao(LicenciaDao licenciaDao) {
		this.licenciaDao = licenciaDao;
	}
	
	public LicenciaDao getLicenciaDao() {
		return licenciaDao;
	}

	public DetalleLicenciaDao getDetalleLicenciaDao() {
		return detalleLicenciaDao;
	}
	
	public void setDetalleLicenciaDao(DetalleLicenciaDao detalleLicenciaDao) {
		this.detalleLicenciaDao = detalleLicenciaDao;
	}

	public List<ExamenDetalle> getDetalleLicenciaPorCliente(String licNro) throws Exception {
		List<ExamenDetalle> detalleExamenes=new ArrayList<ExamenDetalle>();
		
		Licencia lic=new Licencia();
		lic.setLicNro(licNro);
		
		List<Licencia> licencias=licenciaDao.getAll(lic);
		
		if(licencias.size()>0)
		{
			lic=licencias.get(0);
		}
		else
		{
			throw new RuntimeException("No existe el nro de licencia ingresado.");
		}
		
		DetalleLicencia detalleLicencia=new DetalleLicencia();
		detalleLicencia.setLicencia(lic);
		List<DetalleLicencia> detallesLicencias= detalleLicenciaDao.getAll(detalleLicencia);
		
		for(DetalleLicencia detalleLicenciaAux:detallesLicencias)
		{
			if(detalleLicenciaAux.getDlicActivaSn().equalsIgnoreCase("S"))
				detalleExamenes.add(detalleLicenciaAux.getExamenDetalle());
		}
		
		return detalleExamenes;
	}
}
