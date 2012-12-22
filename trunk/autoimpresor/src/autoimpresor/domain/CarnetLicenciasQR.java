package autoimpresor.domain;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import testerGeneral.domain.Dominio;
import autoimpresor.business.ContextManager;
import frontend.utils.Util;




/**
 * CarnetLicencias entity. @author MyEclipse Persistence Tools
 */

public class CarnetLicenciasQR extends  CarnetLicenciasExtendida implements java.io.Serializable {

	private static final long serialVersionUID = -374384809011033049L;
	
    //QR
	private String formatoLicencia;
    private Integer perDomNro;
    private Integer perDomNroPiso;     
    private String perDomLetraDpt;
    private String perDomCodigoPostal;
    private String perDomBarrio;
    
    private String perDomPais;
    private String perDomProvincia;
    private String perDomDepartamento;
    private String perDomLocalidad;
    
    private String munDomPais;
    private String munDomProvincia;
    private String munDomDepartamento;
    private String munDomLocalidad;
    
    private String datosQR;
    private byte[] qr=new byte[1];
    
        
    //QR
    
    // Constructors
    /** default constructor */
    
    public CarnetLicenciasQR(Licencia lic,String nombreMunicipio,String codigoMunicipio,byte[] escudoMunicipio,String formato) {
    	super(lic,nombreMunicipio,codigoMunicipio,escudoMunicipio);
		try
		{			
			actualizarFechas();
	        this.munDomPais=ContextManager.getProperty("SISTEMA.PAIZ.MUNICIPIO");
	        this.munDomProvincia=ContextManager.getProperty("SISTEMA.PROVINCIA.MUNICIPIO");
	        this.munDomDepartamento=ContextManager.getProperty("SISTEMA.DEPARTAMENTO.MUNICIPIO");
	        this.munDomLocalidad=ContextManager.getProperty("SISTEMA.LOCALIDAD.MUNICIPIO");
	        
    		if(formato==null)
    			this.formatoLicencia=ContextManager.getProperty("LICENCIA.FORMATO");
    		else
    			this.formatoLicencia=formato;
    		
	    	this.perDomNro=lic.getPersona().getPerDomNro();
	    	this.perDomNroPiso=lic.getPersona().getPerDomNroPiso();
	    	this.perDomLetraDpt=lic.getPersona().getPerDomLetraDpt();
	    	this.perDomCodigoPostal=lic.getPersona().getPerDomCodigoPostal();
	    	this.perDomBarrio=lic.getPersona().getPerDomBarrio();
	    	this.perDomPais=lic.getPersona().getPerDomPais();
	    	this.perDomProvincia=lic.getPersona().getPerDomProvincia();
	    	this.perDomDepartamento=lic.getPersona().getPerDomDepartamento();
	    	this.perDomLocalidad=lic.getPersona().getPerDomLocalidad();
	    	
	    	List<Dominio> nacionalidades=Util.getDominios("Nacionalidad");
	    	for(Dominio nac:nacionalidades)
	    	{
	    		if(nac.getDomCodigo().equals(getPerNacionalidad()))
	    		{
	    			setPerNacionalidad(nac.getDomValorMostrar());
	    			break;
	    		}
	    		
	    	}
	    	
	        
			
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
    }
    
    public void actualizarFechas() 
    {
    	try
    	{
			SimpleDateFormat sdf=new SimpleDateFormat(Util.formatoFecha); 
			this.setLicFechaOtorgada(new Timestamp(sdf.parse(this.getLicFechaOtorgadaTxt()).getTime()));
			this.setLicFechaVencimiento(new Timestamp(sdf.parse(this.getLicFechaVencimientoTxt()).getTime()));
			this.setPerFechaNacimiento(new Timestamp(sdf.parse(this.getPerFechaNacimientoTxt()).getTime()));
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
    }
    
    public CarnetLicenciasQR() {
    }

	public Integer getPerDomNro() {
		return perDomNro;
	}

	public void setPerDomNro(Integer perDomNro) {
		this.perDomNro = perDomNro;
	}

	public Integer getPerDomNroPiso() {
		return perDomNroPiso;
	}

	public void setPerDomNroPiso(Integer perDomNroPiso) {
		this.perDomNroPiso = perDomNroPiso;
	}

	public String getPerDomLetraDpt() {
		return perDomLetraDpt;
	}

	public void setPerDomLetraDpt(String perDomLetraDpt) {
		this.perDomLetraDpt = perDomLetraDpt;
	}

	public String getPerDomCodigoPostal() {
		return perDomCodigoPostal;
	}

	public void setPerDomCodigoPostal(String perDomCodigoPostal) {
		this.perDomCodigoPostal = perDomCodigoPostal;
	}

	public String getPerDomBarrio() {
		return perDomBarrio;
	}

	public void setPerDomBarrio(String perDomBarrio) {
		this.perDomBarrio = perDomBarrio;
	}

	public String getPerDomPais() {
		return perDomPais;
	}

	public void setPerDomPais(String perDomPais) {
		this.perDomPais = perDomPais;
	}

	public String getPerDomProvincia() {
		return perDomProvincia;
	}

	public void setPerDomProvincia(String perDomProvincia) {
		this.perDomProvincia = perDomProvincia;
	}

	public String getPerDomDepartamento() {
		return perDomDepartamento;
	}

	public void setPerDomDepartamento(String perDomDepartamento) {
		this.perDomDepartamento = perDomDepartamento;
	}

	public String getPerDomLocalidad() {
		return perDomLocalidad;
	}

	public void setPerDomLocalidad(String perDomLocalidad) {
		this.perDomLocalidad = perDomLocalidad;
	}

	public String getMunDomPais() {
		return munDomPais;
	}

	public void setMunDomPais(String munDomPais) {
		this.munDomPais = munDomPais;
	}

	public String getMunDomProvincia() {
		return munDomProvincia;
	}

	public void setMunDomProvincia(String munDomProvincia) {
		this.munDomProvincia = munDomProvincia;
	}

	public String getMunDomDepartamento() {
		return munDomDepartamento;
	}

	public void setMunDomDepartamento(String munDomDepartamento) {
		this.munDomDepartamento = munDomDepartamento;
	}

	public String getMunDomLocalidad() {
		return munDomLocalidad;
	}

	public void setMunDomLocalidad(String munDomLocalidad) {
		this.munDomLocalidad = munDomLocalidad;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	public String getFormatoLicencia() {
		return formatoLicencia;
	}
	
	public void setFormatoLicencia(String formatoLicencia) {
		this.formatoLicencia = formatoLicencia;
	}    
 
	public String getDatosQR() {
		return datosQR;
	}
	
	public void setDatosQR(String datosQR) {
		this.datosQR = datosQR;
	}
	
	public byte[] getQr() {
		return qr;
	}
	
	public void setQr(byte[] qr) {
		this.qr = qr;
	}
}