package testerGeneral.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PersonaExamen entity. @author MyEclipse Persistence Tools
 */

public class PersonaExamen implements java.io.Serializable {

	// Fields

	private Long pexaId;
	private Examen examen;
	private Persona persona;
	private Date pexaFecha;
	private String pexaResultado;
	private String pexaEstado;
	private Double pexaNota;
	private String pexaObs;
	private String pexaTipoExamen;
	private byte pexaAdj[]=new byte[1];
	private String pexaNombreAdjunto;
	private String pexaResultadoMedico;
	private Set resultadoDetalleExamens = new HashSet(0);

	// Constructors

	/** default constructor */
	public PersonaExamen() {
	}


	// Property accessors

	public Long getPexaId() {
		return this.pexaId;
	}

	public void setPexaId(Long pexaId) {
		this.pexaId = pexaId;
	}

	public Examen getExamen() {
		return this.examen;
	}

	public void setExamen(Examen examen) {
		this.examen = examen;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Date getPexaFecha() {
		return this.pexaFecha;
	}

	public void setPexaFecha(Date pexaFecha) {
		this.pexaFecha = pexaFecha;
	}

	public String getPexaResultado() {
		return this.pexaResultado;
	}

	public void setPexaResultado(String pexaResultado) {
		this.pexaResultado = pexaResultado;
	}

	public String getPexaEstado() {
		return this.pexaEstado;
	}

	public void setPexaEstado(String pexaEstado) {
		this.pexaEstado = pexaEstado;
	}

	public Double getPexaNota() {
		return this.pexaNota;
	}

	public void setPexaNota(Double pexaNota) {
		this.pexaNota = pexaNota;
	}

	public String getPexaObs() {
		return this.pexaObs;
	}

	public void setPexaObs(String pexaObs) {
		this.pexaObs = pexaObs;
	}

	public byte[] getPexaAdj() {
		return this.pexaAdj;
	}

	public void setPexaAdj(byte[] pexaAdj) {
		this.pexaAdj = pexaAdj;
	}

	public String getPexaNombreAdjunto() {
		return pexaNombreAdjunto;
	}


	public void setPexaNombreAdjunto(String pexaNombreAdjunto) {
		this.pexaNombreAdjunto = pexaNombreAdjunto;
	}


	public String getPexaResultadoMedico() {
		return this.pexaResultadoMedico;
	}

	public void setPexaResultadoMedico(String pexaResultadoMedico) {
		this.pexaResultadoMedico = pexaResultadoMedico;
	}

	public Set getResultadoDetalleExamens() {
		return this.resultadoDetalleExamens;
	}

	public void setResultadoDetalleExamens(Set resultadoDetalleExamens) {
		this.resultadoDetalleExamens = resultadoDetalleExamens;
	}
	
	public String getPexaTipoExamen() {
		return pexaTipoExamen;
	}
	
	public void setPexaTipoExamen(String pexaTipoExamen) {
		this.pexaTipoExamen = pexaTipoExamen;
	}
}