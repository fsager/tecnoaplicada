package ar.com.tecnologiaaplicada.service.impl;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;


import ar.com.tecnologiaaplicada.domain.ExamenDetalle;

@WebService
public interface LicenseServiceEndpoint{
	
	@WebMethod
	public List<ExamenDetalle> getDetalleLicenciaPorCliente(String licNro) throws Exception;
	
	@WebMethod
	public String getDetalleLicenciaPorCliente1(String licNro) throws Exception;
}