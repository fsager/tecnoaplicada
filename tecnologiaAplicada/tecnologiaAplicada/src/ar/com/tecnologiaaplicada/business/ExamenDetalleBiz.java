package ar.com.tecnologiaaplicada.business;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;

import ar.com.tecnologiaaplicada.Encriptadora;
import ar.com.tecnologiaaplicada.LicenseException;
import ar.com.tecnologiaaplicada.domain.Activacion;
import ar.com.tecnologiaaplicada.domain.DetalleLicencia;
import ar.com.tecnologiaaplicada.domain.ExamenDetalle;
import ar.com.tecnologiaaplicada.domain.Licencia;
import ar.com.tecnologiaaplicada.persistence.ActivacionDao;
import ar.com.tecnologiaaplicada.persistence.DetalleLicenciaDao;
import ar.com.tecnologiaaplicada.persistence.ExamenDetalleDao;
import ar.com.tecnologiaaplicada.persistence.LicenciaDao;
import ar.com.tecnologiaaplicada.service.ExamenDetalleDefinition;

public class ExamenDetalleBiz implements ExamenDetalleDefinition {
	ExamenDetalleDao dao;
	LicenciaDao licenciaDao;
	DetalleLicenciaDao detalleLicenciaDao;
	ActivacionDao activacionDao;
	
	public ActivacionDao getActivacionDao() {
		return activacionDao;
	}
	
	public void setActivacionDao(ActivacionDao activacionDao) {
		this.activacionDao = activacionDao;
	}
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

	public byte[] getDetalleLicenciaPorCliente(String licNro,String producto,String codigoActivacion) throws Exception {
		List<ExamenDetalle> detalleExamenes=new ArrayList<ExamenDetalle>();
		
		Licencia lic=new Licencia();
		lic.setLicNro(licNro);
		lic.setLicProducto(producto);
		
		List<Licencia> licencias=licenciaDao.getAll(lic);
		
		if(licencias.size()>0)
		{
			lic=licencias.get(0);
			lic.setLicFechaActualizacion(new Date());
		}
		else
		{
			throw new LicenseException("No existe el nro. de licencia ingresado. Nro. Licencia: "+licNro+", Producto: "+producto+".");
		}
		
		if(lic.getLicActivaSn().equals("N"))
		{
			throw new LicenseException("La licencia: "+licNro+" para el producto: "+producto+" se encuentra inactiva. Por favor contáctese con el proveedor del sistema.");
		}
		
		Activacion act=new Activacion();
		act.setLicencia(lic);
		act.setActFecha(new Date());
		act.setActCodigo(codigoActivacion);
		isValidActivationCode(act);
		activacionDao.insert(act);
		
		DetalleLicencia detalleLicencia=new DetalleLicencia();
		detalleLicencia.setLicencia(lic);
		List<DetalleLicencia> detallesLicencias= detalleLicenciaDao.getAll(detalleLicencia);
		lic.setDetalleLicencias(detallesLicencias);
		/*for(DetalleLicencia detalleLicenciaAux:detallesLicencias)
		{
			if(detalleLicenciaAux.getDlicActivaSn().equalsIgnoreCase("S"))
				detalleExamenes.add(detalleLicenciaAux.getExamenDetalle());
		}*/
		
		return encriptarLicencia(lic);
	}
	
	private boolean isValidActivationCode(Activacion actActual) throws LicenseException
	{
		System.out.println("actActual.getActFechaCodigo: "+actActual.getActFechaCodigo());
		System.out.println("actActual.getActInfo: "+actActual.getActInfo());

		try
		{
			//Codigo en Hexadecimal
			String codigoActivacion=actActual.getActCodigo();
			
			//Convierto el codigo a base decimal
			//BigDecimal big=new BigDecimal(codigoActivacion,)
			Long codigoInDecimalBase=Long.valueOf(codigoActivacion,16);
			String strCodigoInDecimalBase =codigoInDecimalBase+"";		
			String digitoVerificadorInformado=strCodigoInDecimalBase.substring(strCodigoInDecimalBase.length()-1);
			
			
			String digitoVerificadorCalculado=getDigitoVerificador(strCodigoInDecimalBase.substring(0,strCodigoInDecimalBase.length()-1));
			
			if(!digitoVerificadorInformado.equals(digitoVerificadorCalculado)){
				throw new LicenseException("El código de activación no es válido");
			}
			
		}catch(Exception e)
		{
			throw new LicenseException("El código de activación no es válido");
		}
		
		List <Activacion> activaciones=null;
		Activacion actUltima=null;
		try
		{
			actUltima=new Activacion();
			actUltima.setLicencia(actActual.getLicencia());
			activaciones=activacionDao.getAll(actUltima);
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
			
		if(activaciones.size()>0)
		{
			actUltima=activaciones.get(0);
			if(!actUltima.getActFechaCodigo().before(actActual.getActFechaCodigo()))
				throw new LicenseException("El código de activación ya ha sigo utilizado");
		}
		
		return true;
	}
	
	public static String getDigitoVerificador(String codigoActivacion)
	{
		int[] secuencia={1,3,5,7,9,3,5,7,9,3,5,7,9,3,5,7,9,3,5,7,9,3,5,7,9,3,5,7,9,3,5,7,9,3,5,7,9,3,5,7,9};//,3,5
		int prod=0;
		
		for(int i=0;i<secuencia.length && i<codigoActivacion.length();i++)
		{
			
			int res=secuencia[i]*Integer.valueOf(""+codigoActivacion.charAt(i));
			prod+=res;
		}
		
		int result=prod/2;
		
		String unidad=String.valueOf(result);
		
		return unidad.substring(unidad.length()-1);
	}
	
	private byte[] encriptarLicencia(Licencia lic)
	{
		byte[] bytes = null;
		  ByteArrayOutputStream bos = new ByteArrayOutputStream();
		  try {
		    ObjectOutputStream oos = new ObjectOutputStream(bos); 
		    oos.writeObject(lic);
		    oos.flush(); 
		    oos.close(); 
		    bos.close();
		    bytes = bos.toByteArray ();
		    
		    String clave=lic.getLicNro();
		    //completo la longitud de la clave
		    while(clave.length()<16)
		    {
		    	clave+=1;
		    }
		    
			SecretKeySpec clavePrivada = new SecretKeySpec(clave.getBytes(), "AES");
			Encriptadora encriptador = new Encriptadora("AES", clavePrivada);
			bytes= encriptador.encriptar(bytes);			
		  }
		  catch (IOException ex) {
		    throw new RuntimeException(ex);
		  }
		  return bytes;
	}
}
