package autoimpresor.domain;

import java.text.SimpleDateFormat;
import java.util.List;

import autoimpresor.business.ContextManager;
import autoimpresor.service.ClaseLicenciaDefinition;
import frontend.utils.Util;




/**
 * CarnetLicencias entity. @author MyEclipse Persistence Tools
 */

public class CarnetLicenciasExtendida extends  CarnetLicencias implements java.io.Serializable {


    // Fields   
    private String perFechaNacimientoTxt;
    private String licFechaOtorgadaTxt;
    private String licFechaVencimientoTxt;
    
    // Constructors
    /** default constructor */
    
    public CarnetLicenciasExtendida(Licencia lic,String nombreMunicipio,String codigoMunicipio,byte[] escudoMunicipio) {
		try
		{
	    	ClaseLicenciaDefinition claseLicenciaService = (ClaseLicenciaDefinition) ContextManager.getBizObject("claseLicenciaService");
	    	
			this.setPerApellido(lic.getPersona().getPerApellido());
			this.setPerNombre(lic.getPersona().getPerNombre());
			this.setPerTipoDoc(lic.getPersona().getPerTipoDoc());
			this.setPerNumeroDoc(lic.getPersona().getPerNumeroDoc());
			this.setPerNacionalidad(lic.getPersona()
					.getPerNacionalidad());
			this.setPerGrupoSanguineo(lic.getPersona()
					.getPerGrupoSanguineo());
			this.setPerTelefono(lic.getPersona().getPerTelefono());
			this.setPerDonante(lic.getPersona().getPerDonante());
			this.setPerAlergia(lic.getPersona().getPerAlergia());
			this.setPerRestricciones(lic.getPersona()
					.getPerRestricciones());
			this.setPerMedicacion(lic.getPersona()
					.getPerMedicacion());
			this.setPerDomicilio(lic.getPersona().getPerDomicilio());
			this.setPerObservaciones(lic.getLicObservaciones());
			this.setPerFoto(lic.getPersona().getPerFoto());
			this.setPerFirma(lic.getPersona().getPerFirma());
			this.setLicClase(lic.getLicClase());
	
			ClaseLicencia clase = new ClaseLicencia();
			clase.setCllNombreClase(lic.getLicClase());
			List<ClaseLicencia> lstClase = claseLicenciaService.getAll(clase);
			this.setLicClaseDesc(lstClase.get(0).getCllDescripcion());
			this.setLicTramite(lic.getLicTramite());

			this.setUsrNombre(lic.getUsuarioByUsrNombreFirma()
					.getUsrNombre());
			this.setUsrCargo(lic.getUsuarioByUsrNombreFirma()
					.getUsrCargo());
			this.setUsrFirma(lic.getUsuarioByUsrNombreFirma()
					.getUsrFirma());
			this.setLicCodLicencia(lic.getLicCodLicencia());
	
			SimpleDateFormat sdf=new SimpleDateFormat(Util.formatoFecha); 
			this.setLicFechaOtorgadaTxt(sdf.format(lic.getLicFechaOtorgada()));
			this.setLicFechaVencimientoTxt(sdf.format(lic.getLicFechaVencimiento()));
			this.setPerFechaNacimientoTxt(sdf.format(lic.getPersona().getPerFechaNacimiento()));
			
			
			this.setMncNombre(nombreMunicipio);
			this.setMncCodigo(codigoMunicipio);
			this.setMncEscudo(escudoMunicipio);
			
			
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
    }
    
    public CarnetLicenciasExtendida() {
    }

	public String getPerFechaNacimientoTxt() {
		return perFechaNacimientoTxt;
	}

	public void setPerFechaNacimientoTxt(String perFechaNacimientoTxt) {
		this.perFechaNacimientoTxt = perFechaNacimientoTxt;
	}

	public String getLicFechaOtorgadaTxt() {
		return licFechaOtorgadaTxt;
	}

	public void setLicFechaOtorgadaTxt(String licFechaOtorgadaTxt) {
		this.licFechaOtorgadaTxt = licFechaOtorgadaTxt;
	}

	public String getLicFechaVencimientoTxt() {
		return licFechaVencimientoTxt;
	}

	public void setLicFechaVencimientoTxt(String licFechaVencimientoTxt) {
		this.licFechaVencimientoTxt = licFechaVencimientoTxt;
	}    
}