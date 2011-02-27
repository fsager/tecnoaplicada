package ar.com.tecnologiaaplicada.service.impl;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import ar.com.tecnologiaaplicada.domain.ExamenDetalle;
import ar.com.tecnologiaaplicada.service.ExamenDetalleDefinition;

@WebService(serviceName = "LicenseService")
public class LicenseServiceEndpointImp extends SpringBeanAutowiringSupport implements LicenseServiceEndpoint{

	public LicenseServiceEndpointImp() {
		System.out.println("LicenseService WS");
	}

	@Autowired
	@Qualifier("examenDetalleBusiness")
	private ExamenDetalleDefinition examenDetalleService;

	@WebMethod
	public List<ExamenDetalle> getDetalleLicenciaPorCliente(String licNro) throws Exception {		
		return examenDetalleService.getDetalleLicenciaPorCliente(licNro);
	}
	
	@WebMethod
	public String getDetalleLicenciaPorCliente1(String licNro) throws Exception {		
		return "FEDE";
	}

}