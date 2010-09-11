package autoimpresor.domain;


import java.util.HashSet;
import java.util.Set;



/**
 * Persona entity. @author MyEclipse Persistence Tools
 */

public class Persona  implements java.io.Serializable {


    // Fields    

     private Long perId;
     private String perApellido;
     private String perNombre;
     private String perTipoDoc;
     private String perNumeroDoc;
     private String perNacionalidad;
     private String perSexo;
     private java.util.Date perFechaNacimiento;
     private String perGrupoSanguineo;
     private String perTelefono;
     private String perDonante;
     private String perAlergia;
     private String perRestricciones;
     private String perMedicacion;
     private String perDomicilio;
     private String perObservaciones;
	 private byte perFoto[]=new byte[1];
	 private byte perFirma[]=new byte[1];
     private Set licencias = new HashSet(0);


    // Constructors

    /** default constructor */
    public Persona() {
    }

   
    // Property accessors

    public Long getPerId() {
        return this.perId;
    }
    
    public void setPerId(Long perId) {
        this.perId = perId;
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

    public String getPerSexo() {
        return this.perSexo;
    }
    
    public void setPerSexo(String perSexo) {
        this.perSexo = perSexo;
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
    	if(perTelefono!= null && !perTelefono.equals(""))
    		this.perTelefono = perTelefono;
    	else
    		this.perTelefono = "-";
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
    	if(perAlergia!= null && !perAlergia.equals(""))
    		this.perAlergia = perAlergia;
    	else
    		this.perAlergia = "NO";
    }

    public String getPerRestricciones() {
        return this.perRestricciones;
    }
    
    public void setPerRestricciones(String perRestricciones) {
    	if(perRestricciones!= null && !perRestricciones.equals(""))
    		this.perRestricciones = perRestricciones;
    	else
    		this.perRestricciones = "NO";
    }

    public String getPerMedicacion() {
        return this.perMedicacion;
    }
    
    public void setPerMedicacion(String perMedicacion) {
    	if(perMedicacion!= null && !perMedicacion.equals(""))
    		this.perMedicacion = perMedicacion;
    	else
    		this.perMedicacion = "NO";
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
    	if(perObservaciones!= null && !perObservaciones.equals(""))
    		this.perObservaciones = perObservaciones;
    	else
    		this.perObservaciones = "NO";
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

    public Set getLicencias() {
        return this.licencias;
    }
    
    public void setLicencias(Set licencias) {
        this.licencias = licencias;
    }

	public String getPerNombreCompleto() {
		return this.perApellido+", "+perNombre;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((perId == null) ? 0 : perId.hashCode());
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
		Persona other = (Persona) obj;
		if (perId == null) {
			if (other.perId != null)
				return false;
		} else if (!perId.equals(other.perId))
			return false;
		return true;
	}
}