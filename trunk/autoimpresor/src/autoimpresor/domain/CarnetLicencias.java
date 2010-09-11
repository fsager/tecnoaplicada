package autoimpresor.domain;

import java.util.Date;
import java.util.List;

import autoimpresor.business.ContextManager;
import autoimpresor.service.ClaseLicenciaDefinition;




/**
 * CarnetLicencias entity. @author MyEclipse Persistence Tools
 */

public class CarnetLicencias  implements java.io.Serializable {


    // Fields    

     private Long cliId;
     private Date cliFechaImpresion;
     private Date cliFechaImport;
     private Long cliCantImpresiones;
     private String perApellido;
     private String perNombre;
     private String perTipoDoc;
     private String perNumeroDoc;
     private String perNacionalidad;
     private java.util.Date perFechaNacimiento;
     private String perGrupoSanguineo;
     private String perTelefono;
     private String perDonante;
     private String perAlergia;
     private String perRestricciones;
     private String perMedicacion;
     private String perDomicilio;
     private String perObservaciones;
     private byte[] perFoto=new byte[1];
     private byte[] perFirma=new byte[1];
     private String licClase;
     private String licClaseDesc;
     private String licTramite;
     private java.util.Date licFechaOtorgada;
     private java.util.Date licFechaVencimiento;
     private String usrNombre;
     private String usrCargo;
     private byte[] usrFirma=new byte[1];
     private String licCodLicencia;
     private String mncNombre;
     private String mncCodigo;
     private byte[] mncEscudo=new byte[1];


    // Constructors

    /** default constructor */
    public CarnetLicencias() {
    }

    public CarnetLicencias(Licencia lic,String nombreMunicipio,String codigoMunicipio,byte[] escudoMunicipio) {
		try
		{
	    	ClaseLicenciaDefinition claseLicenciaService = (ClaseLicenciaDefinition) ContextManager.getBizObject("claseLicenciaService");
	    	
			this.setPerApellido(lic.getPersona().getPerApellido());
			this.setPerNombre(lic.getPersona().getPerNombre());
			this.setPerTipoDoc(lic.getPersona().getPerTipoDoc());
			this.setPerNumeroDoc(lic.getPersona().getPerNumeroDoc());
			this.setPerNacionalidad(lic.getPersona()
					.getPerNacionalidad());
			this.setPerFechaNacimiento(lic.getPersona()
					.getPerFechaNacimiento());
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
			this.setPerObservaciones(lic.getPersona()
					.getPerObservaciones());
			this.setPerFoto(lic.getPersona().getPerFoto());
			this.setPerFirma(lic.getPersona().getPerFirma());
			this.setLicClase(lic.getLicClase());
	
			ClaseLicencia clase = new ClaseLicencia();
			clase.setCllNombreClase(lic.getLicClase());
			List<ClaseLicencia> lstClase = claseLicenciaService.getAll(clase);
			this.setLicClaseDesc(lstClase.get(0).getCllDescripcion());
			this.setLicTramite(lic.getLicTramite());
			this.setLicFechaOtorgada(lic.getLicFechaOtorgada());
			this.setLicFechaVencimiento(lic
							.getLicFechaVencimiento());
			this.setUsrNombre(lic.getUsuarioByUsrNombreFirma()
					.getUsrNombre());
			this.setUsrCargo(lic.getUsuarioByUsrNombreFirma()
					.getUsrCargo());
			this.setUsrFirma(lic.getUsuarioByUsrNombreFirma()
					.getUsrFirma());
			this.setLicCodLicencia(lic.getLicCodLicencia());
	
			this.setMncNombre(nombreMunicipio);
			this.setMncCodigo(codigoMunicipio);
			this.setMncEscudo(escudoMunicipio);
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
    }
    
    // Property accessors

    public Long getCliId() {
        return this.cliId;
    }
    
    public void setCliId(Long cliId) {
        this.cliId = cliId;
    }

    public String getPerApellido() {
        return this.perApellido;
    }
    
    public void setPerApellido(String perApellido) {
        this.perApellido = perApellido;
    }

    public String getPerNombre() {
        return this.perNombre;
    }
    
    public void setPerNombre(String perNombre) {
        this.perNombre = perNombre;
    }

    public String getPerTipoDoc() {
        return this.perTipoDoc;
    }
    
    public void setPerTipoDoc(String perTipoDoc) {
        this.perTipoDoc = perTipoDoc;
    }

    public String getPerNumeroDoc() {
        return this.perNumeroDoc;
    }
    
    public void setPerNumeroDoc(String perNumeroDoc) {
        this.perNumeroDoc = perNumeroDoc;
    }

    public String getPerNacionalidad() {
        return this.perNacionalidad;
    }
    
    public void setPerNacionalidad(String perNacionalidad) {
        this.perNacionalidad = perNacionalidad;
    }

    public java.util.Date getPerFechaNacimiento() {
        return this.perFechaNacimiento;
    }
    
    public void setPerFechaNacimiento(java.util.Date perFechaNacimiento) {
        this.perFechaNacimiento = perFechaNacimiento;
    }

    public String getPerGrupoSanguineo() {
        return this.perGrupoSanguineo;
    }
    
    public void setPerGrupoSanguineo(String perGrupoSanguineo) {
        this.perGrupoSanguineo = perGrupoSanguineo;
    }

    public String getPerTelefono() {
        return this.perTelefono;
    }
    
    public void setPerTelefono(String perTelefono) {
        this.perTelefono = perTelefono;
    }

    public String getPerDonante() {
        return this.perDonante;
    }
    
    public void setPerDonante(String perDonante) {
        this.perDonante = perDonante;
    }

    public String getPerAlergia() {
        return this.perAlergia;
    }
    
    public void setPerAlergia(String perAlergia) {
        this.perAlergia = perAlergia;
    }

    public String getPerRestricciones() {
        return this.perRestricciones;
    }
    
    public void setPerRestricciones(String perRestricciones) {
        this.perRestricciones = perRestricciones;
    }

    public String getPerMedicacion() {
        return this.perMedicacion;
    }
    
    public void setPerMedicacion(String perMedicacion) {
        this.perMedicacion = perMedicacion;
    }

    public String getPerDomicilio() {
        return this.perDomicilio;
    }
    
    public void setPerDomicilio(String perDomicilio) {
        this.perDomicilio = perDomicilio;
    }

    public String getPerObservaciones() {
        return this.perObservaciones;
    }
    
    public void setPerObservaciones(String perObservaciones) {
        this.perObservaciones = perObservaciones;
    }

    public byte[] getPerFoto() {
        return this.perFoto;
    }
    
    public void setPerFoto(byte[] perFoto) {
        this.perFoto = perFoto;
    }

    public byte[] getPerFirma() {
        return this.perFirma;
    }
    
    public void setPerFirma(byte[] perFirma) {
        this.perFirma = perFirma;
    }

    public String getLicClase() {
        return this.licClase;
    }
    
    public void setLicClase(String licClase) {
        this.licClase = licClase;
    }

    public String getLicTramite() {
        return this.licTramite;
    }
    
    public void setLicTramite(String licTramite) {
        this.licTramite = licTramite;
    }

    public java.util.Date getLicFechaOtorgada() {
        return this.licFechaOtorgada;
    }
    
    public void setLicFechaOtorgada(java.util.Date licFechaOtorgada) {
        this.licFechaOtorgada = licFechaOtorgada;
    }

    public java.util.Date getLicFechaVencimiento() {
        return this.licFechaVencimiento;
    }
    
    public void setLicFechaVencimiento(java.util.Date licFechaVencimiento) {
        this.licFechaVencimiento = licFechaVencimiento;
    }

    public String getUsrNombre() {
        return this.usrNombre;
    }
    
    public void setUsrNombre(String usrNombre) {
        this.usrNombre = usrNombre;
    }

    public String getUsrCargo() {
        return this.usrCargo;
    }
    
    public void setUsrCargo(String usrCargo) {
        this.usrCargo = usrCargo;
    }

    public byte[] getUsrFirma() {
        return this.usrFirma;
    }
    
    public void setUsrFirma(byte[] usrFirma) {
        this.usrFirma = usrFirma;
    }

    public String getLicCodLicencia() {
        return this.licCodLicencia;
    }
    
    public void setLicCodLicencia(String licCodLicencia) {
        this.licCodLicencia = licCodLicencia;
    }

    public String getMncNombre() {
        return this.mncNombre;
    }
    
    public void setMncNombre(String mncNombre) {
        this.mncNombre = mncNombre;
    }

    public String getMncCodigo() {
        return this.mncCodigo;
    }
    
    public void setMncCodigo(String mncCodigo) {
        this.mncCodigo = mncCodigo;
    }

    public byte[] getMncEscudo() {
        return this.mncEscudo;
    }
    
    public void setMncEscudo(byte[] mncEscudo) {
        this.mncEscudo = mncEscudo;
    }
   
	public Date getCliFechaImpresion() {
		return cliFechaImpresion;
	}
	
	public void setCliFechaImpresion(Date cliFechaImpresion) {
		this.cliFechaImpresion = cliFechaImpresion;
	}
	
	public String getPerNombreCompleto() {
		return this.perApellido+", "+perNombre;
	}
	
	
	public Date getCliFechaImport() {
		return cliFechaImport;
	}

	public void setCliFechaImport(Date cliFechaImport) {
		this.cliFechaImport = cliFechaImport;
	}
	
	public Long getCliCantImpresiones() {
		return cliCantImpresiones;
	}
	
	public void setCliCantImpresiones(Long cliCantImpresiones) {
		this.cliCantImpresiones = cliCantImpresiones;
	}
	
	public String getLicClaseDesc() {
		return licClaseDesc;
	}
	
	public void setLicClaseDesc(String licClaseDesc) {
		this.licClaseDesc = licClaseDesc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((licClase == null) ? 0 : licClase.hashCode());
		result = prime * result
				+ ((licCodLicencia == null) ? 0 : licCodLicencia.hashCode());
		result = prime * result
				+ ((licTramite == null) ? 0 : licTramite.hashCode());
		result = prime * result
				+ ((mncCodigo == null) ? 0 : mncCodigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarnetLicencias other = (CarnetLicencias) obj;
		if (licClase == null) {
			if (other.licClase != null)
				return false;
		} else if (!licClase.equals(other.licClase))
			return false;
		if (licCodLicencia == null) {
			if (other.licCodLicencia != null)
				return false;
		} else if (!licCodLicencia.equals(other.licCodLicencia))
			return false;
		if (licTramite == null) {
			if (other.licTramite != null)
				return false;
		} else if (!licTramite.equals(other.licTramite))
			return false;
		if (mncCodigo == null) {
			if (other.mncCodigo != null)
				return false;
		} else if (!mncCodigo.equals(other.mncCodigo))
			return false;
		return true;
	}
	
	
		
}