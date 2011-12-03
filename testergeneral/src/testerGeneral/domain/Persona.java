package testerGeneral.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Persona entity. @author MyEclipse Persistence Tools
 */

public class Persona implements java.io.Serializable {

	// Fields

	private Long perId;
	private String perApellido;
	private String perNombre;
	private String perTipoDoc;
	private String perNumeroDoc;
	private String perSexo;
	private String perLocalidad;
	private String perDomicilio;
	private Date perFechaNacimiento;
	private String perEstadoCivil;
	private String perGrupoSanguineo;
	private String perTelefono;
	private String perCelular;
	private String perCorreo;
	private String perEstudios;
	private String perObservaciones;
	private String perEducacion;
	private String perLicenciaDeConducir;
	private String perTiempoQueLlevaConduciendo;
	private String perTomaMedicamentosRegularmenteSN;
	private String perTomoHoyPsicofarmacosSN;
	private String perTomoHoyAlcohol;
	private byte perFoto[] = new byte[1];
	private byte perFirma[] = new byte[1];
	//private Set personaRestricions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Persona() {
	}

	/** minimal constructor */
	public Persona(String perApellido, String perNombre, String perTipoDoc,
			String perNumeroDoc, String perSexo) {
		this.perApellido = perApellido;
		this.perNombre = perNombre;
		this.perTipoDoc = perTipoDoc;
		this.perNumeroDoc = perNumeroDoc;
		this.perSexo = perSexo;
	}

	/** full constructor */
	public Persona(String perApellido, String perNombre, String perTipoDoc,
			String perNumeroDoc, String perSexo, Date perFechaNacimiento,
			String perEstadoCivil, String perGrupoSanguineo,
			String perTelefono, String perCelular, String perCorreo,
			String perEstudios, String perObservaciones) {
		this.perApellido = perApellido;
		this.perNombre = perNombre;
		this.perTipoDoc = perTipoDoc;
		this.perNumeroDoc = perNumeroDoc;
		this.perSexo = perSexo;
		this.perFechaNacimiento = perFechaNacimiento;
		this.perEstadoCivil = perEstadoCivil;
		this.perGrupoSanguineo = perGrupoSanguineo;
		this.perTelefono = perTelefono;
		this.perCelular = perCelular;
		this.perCorreo = perCorreo;
		this.perEstudios = perEstudios;
		this.perObservaciones = perObservaciones;
	}

	/** full constructor */
	public Persona(String perApellido, String perNombre, String perTipoDoc,
			String perNumeroDoc, String perSexo, Date perFechaNacimiento,
			String perEstadoCivil, String perGrupoSanguineo,
			String perTelefono, String perCelular, String perCorreo,
			String perEstudios, String perObservaciones,
			String perLicenciaDeConducir,
			String perTiempoQueLlevaConduciendo,
			String perTomaMedicamentosRegularmenteSN,
			String perTomoHoyPsicofarmacosSN, String perTomoHoyAlcohol) {
		this.perApellido = perApellido;
		this.perNombre = perNombre;
		this.perTipoDoc = perTipoDoc;
		this.perNumeroDoc = perNumeroDoc;
		this.perSexo = perSexo;
		this.perFechaNacimiento = perFechaNacimiento;
		this.perEstadoCivil = perEstadoCivil;
		this.perGrupoSanguineo = perGrupoSanguineo;
		this.perTelefono = perTelefono;
		this.perCelular = perCelular;
		this.perCorreo = perCorreo;
		this.perEstudios = perEstudios;
		this.perObservaciones = perObservaciones;
		this.perLicenciaDeConducir = perLicenciaDeConducir;
		this.perTiempoQueLlevaConduciendo = perTiempoQueLlevaConduciendo;
		this.perTomaMedicamentosRegularmenteSN = perTomaMedicamentosRegularmenteSN;
		this.perTomoHoyPsicofarmacosSN = perTomoHoyPsicofarmacosSN;
		this.perTomoHoyAlcohol = perTomoHoyAlcohol;
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

	public String getPerSexo() {
		return this.perSexo;
	}

	public void setPerSexo(String perSexo) {
		this.perSexo = perSexo;
	}

	public Date getPerFechaNacimiento() {
		return this.perFechaNacimiento;
	}

	public void setPerFechaNacimiento(Date perFechaNacimiento) {
		this.perFechaNacimiento = perFechaNacimiento;
	}

	public String getPerEstadoCivil() {
		return this.perEstadoCivil;
	}

	public void setPerEstadoCivil(String perEstadoCivil) {
		this.perEstadoCivil = perEstadoCivil;
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

	public String getPerCelular() {
		return this.perCelular;
	}

	public void setPerCelular(String perCelular) {
		this.perCelular = perCelular;
	}

	public String getPerCorreo() {
		return this.perCorreo;
	}

	public void setPerCorreo(String perCorreo) {
		this.perCorreo = perCorreo;
	}

	public String getPerEstudios() {
		return this.perEstudios;
	}

	public void setPerEstudios(String perEstudios) {
		this.perEstudios = perEstudios;
	}

	public String getPerObservaciones() {
		return this.perObservaciones;
	}

	public void setPerObservaciones(String perObservaciones) {
		this.perObservaciones = perObservaciones;
	}

	/*public Set getPersonaRestricions() {
		return this.personaRestricions;
	}

	public void setPersonaRestricions(Set personaRestricions) {
		this.personaRestricions = personaRestricions;
	}*/

	public String getPerNombreCompleto() {
		return this.perApellido + ", " + perNombre;
	}

	public String getPerDomicilio() {
		return perDomicilio;
	}

	public String getPerLocalidad() {
		return perLocalidad;
	}

	public void setPerLocalidad(String perLocalidad) {
		this.perLocalidad = perLocalidad;
	}

	public void setPerDomicilio(String perDomicilio) {
		this.perDomicilio = perDomicilio;
	}

	public byte[] getPerFoto() {
		return perFoto;
	}

	public void setPerFoto(byte[] perFoto) {
		this.perFoto = perFoto;
	}

	public byte[] getPerFirma() {
		return perFirma;
	}

	public void setPerFirma(byte[] perFirma) {
		this.perFirma = perFirma;
	}

	public String getPerLicenciaDeConducir() {
		return perLicenciaDeConducir;
	}

	public void setPerLicenciaDeConducir(String perLicenciaDeConducir) {
		this.perLicenciaDeConducir = perLicenciaDeConducir;
	}

	public String getPerEducacion() {
		return perEducacion;
	}

	public void setPerEducacion(String perEducacion) {
		this.perEducacion = perEducacion;
	}

	public String getPerTiempoQueLlevaConduciendo() {
		return perTiempoQueLlevaConduciendo;
	}

	public void setPerTiempoQueLlevaConduciendo(String perTiempoQueLlevaConduciendo) {
		this.perTiempoQueLlevaConduciendo = perTiempoQueLlevaConduciendo;
	}

	public String getPerTomaMedicamentosRegularmenteSN() {
		return perTomaMedicamentosRegularmenteSN;
	}

	public void setPerTomaMedicamentosRegularmenteSN(
			String perTomaMedicamentosRegularmenteSN) {
		this.perTomaMedicamentosRegularmenteSN = perTomaMedicamentosRegularmenteSN;
	}

	public String getPerTomoHoyPsicofarmacosSN() {
		return perTomoHoyPsicofarmacosSN;
	}

	public void setPerTomoHoyPsicofarmacosSN(String perTomoHoyPsicofarmacosSN) {
		this.perTomoHoyPsicofarmacosSN = perTomoHoyPsicofarmacosSN;
	}

	public String getPerTomoHoyAlcohol() {
		return perTomoHoyAlcohol;
	}

	public void setPerTomoHoyAlcohol(String perTomoHoyAlcohol) {
		this.perTomoHoyAlcohol = perTomoHoyAlcohol;
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