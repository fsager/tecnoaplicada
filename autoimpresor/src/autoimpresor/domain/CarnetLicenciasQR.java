package autoimpresor.domain;

import java.text.SimpleDateFormat;
import java.util.List;

import autoimpresor.business.ContextManager;
import autoimpresor.service.ClaseLicenciaDefinition;
import frontend.utils.Util;




/**
 * CarnetLicencias entity. @author MyEclipse Persistence Tools
 */

public class CarnetLicenciasQR extends  CarnetLicencias implements java.io.Serializable {


    /**
	 * 
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = -374384809011033049L;
	/**
	 * 
	 */
	
    //QR
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
        
    //QR
    
    // Constructors
    /** default constructor */
    
    public CarnetLicenciasQR(Licencia lic,String nombreMunicipio,String codigoMunicipio,byte[] escudoMunicipio
    		,String munDomPais
    		,String munDomProvincia
    		,String munDomDepartamento
    		,String munDomLocalidad) {
    	super(lic,nombreMunicipio,codigoMunicipio,escudoMunicipio);
		try
		{			
	    	this.perDomNro=lic.getPersona().getPerDomNro();
	    	this.perDomNroPiso=lic.getPersona().getPerDomNroPiso();
	    	this.perDomLetraDpt=lic.getPersona().getPerDomLetraDpt();
	    	this.perDomCodigoPostal=lic.getPersona().getPerDomCodigoPostal();
	    	this.perDomBarrio=lic.getPersona().getPerDomBarrio();
	    	this.perDomPais=lic.getPersona().getPerDomPais();
	    	this.perDomProvincia=lic.getPersona().getPerDomProvincia();
	    	this.perDomDepartamento=lic.getPersona().getPerDomDepartamento();
	    	this.perDomLocalidad=lic.getPersona().getPerDomLocalidad();
	        
	        this.munDomPais=munDomPais;
	        this.munDomProvincia=munDomProvincia;
	        this.munDomDepartamento=munDomDepartamento;
	        this.munDomLocalidad=munDomLocalidad;			
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
    
    
}