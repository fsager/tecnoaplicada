package autoimpresor.domain;

import java.util.HashSet;
import java.util.Set;

import testerGeneral.domain.UsuarioCommon;

/**
 * Licencia entity. @author MyEclipse Persistence Tools
 */

public class Licencia implements java.io.Serializable {

	// Fields

	private Long licId;
	private Persona persona;
	private UsuarioCommon usuarioByUsrConfeccionoLicencia;
	private UsuarioCommon usuarioByUsrNombreResponsable;
	private UsuarioCommon usuarioByUsrNombreFirma;
	private String licObservaciones;
	private String licClase;
	private String licTramite;
	private java.util.Date licFechaOtorgada;
	private java.util.Date licFechaVencimiento;
	private String licEstado;
	private String licExamenTeorico;
	private String licExamenPractico;
	private Antecedente antecedente;
	private String licDeudaSn;
	private java.util.Date licFechaReal;
	private String licRequisitosSn;
	private Long licNumero;
	private String licCodLicencia;
	private String licExamenMedico;
	private String licExamenOftalmologico;
	private String licExamenPsicofisico;
	private Double licImporte;
	private Long licRecibo;
	
	private Set cajas = new HashSet(0);
	private Set cajas_1 = new HashSet(0);

	// Constructors

	/** default constructor */
	public Licencia() {
		// this.setLicObservaciones("No");
	}

	// Property accessors

	public Long getLicId() {
		return this.licId;
	}

	public void setLicId(Long licId) {
		this.licId = licId;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public UsuarioCommon getUsuarioByUsrConfeccionoLicencia() {
		return this.usuarioByUsrConfeccionoLicencia;
	}

	public void setUsuarioByUsrConfeccionoLicencia(
			UsuarioCommon usuarioByUsrConfeccionoLicencia) {
		this.usuarioByUsrConfeccionoLicencia = usuarioByUsrConfeccionoLicencia;
	}

	public UsuarioCommon getUsuarioByUsrNombreResponsable() {
		return this.usuarioByUsrNombreResponsable;
	}

	public void setUsuarioByUsrNombreResponsable(
			UsuarioCommon usuarioByUsrNombreResponsable) {
		this.usuarioByUsrNombreResponsable = usuarioByUsrNombreResponsable;
	}

	public UsuarioCommon getUsuarioByUsrNombreFirma() {
		return this.usuarioByUsrNombreFirma;
	}

	public void setUsuarioByUsrNombreFirma(UsuarioCommon usuarioByUsrNombreFirma) {
		this.usuarioByUsrNombreFirma = usuarioByUsrNombreFirma;
	}

	public String getLicObservaciones() {
		return this.licObservaciones;
	}

	public void setLicObservaciones(String licObservaciones) {
		if (licObservaciones != null && !licObservaciones.equals(""))
			this.licObservaciones = licObservaciones;
		else
			this.licObservaciones = "No";
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

	public String getLicEstado() {
		return this.licEstado;
	}

	public void setLicEstado(String licEstado) {
		this.licEstado = licEstado;
	}

	public String getLicExamenTeorico() {
		return this.licExamenTeorico;
	}

	public void setLicExamenTeorico(String licExamenTeorico) {
		this.licExamenTeorico = licExamenTeorico;
	}

	public String getLicExamenPractico() {
		return this.licExamenPractico;
	}

	public void setLicExamenPractico(String licExamenPractico) {
		this.licExamenPractico = licExamenPractico;
	}

	public Antecedente getAntecedente() {
		return this.antecedente;
	}

	public void setAntecedente(Antecedente antecedente) {
		this.antecedente = antecedente;
	}

	public String getLicDeudaSn() {
		return this.licDeudaSn;
	}

	public void setLicDeudaSn(String licDeudaSn) {
		this.licDeudaSn = licDeudaSn;
	}

	public java.util.Date getLicFechaReal() {
		return this.licFechaReal;
	}

	public void setLicFechaReal(java.util.Date licFechaReal) {
		this.licFechaReal = licFechaReal;
	}

	public String getLicRequisitosSn() {
		return this.licRequisitosSn;
	}

	public void setLicRequisitosSn(String licRequisitosSn) {
		this.licRequisitosSn = licRequisitosSn;
	}

	public Long getLicNumero() {
		return this.licNumero;
	}

	public void setLicNumero(Long licNumero) {
		this.licNumero = licNumero;
	}

	public String getLicCodLicencia() {
		return this.licCodLicencia;
	}

	public void setLicCodLicencia(String licCodLicencia) {
		this.licCodLicencia = licCodLicencia;
	}

	public String getLicExamenMedico() {
		return this.licExamenMedico;
	}

	public void setLicExamenMedico(String licExamenMedico) {
		this.licExamenMedico = licExamenMedico;
	}

	public String getLicExamenOftalmologico() {
		return this.licExamenOftalmologico;
	}

	public void setLicExamenOftalmologico(String licExamenOftalmologico) {
		this.licExamenOftalmologico = licExamenOftalmologico;
	}

	public String getLicExamenPsicofisico() {
		return this.licExamenPsicofisico;
	}

	public void setLicExamenPsicofisico(String licExamenPsicofisico) {
		this.licExamenPsicofisico = licExamenPsicofisico;
	}

	public Double getLicImporte() {
		return licImporte;
	}
	
	public void setLicImporte(Double licImporte) {
		this.licImporte = licImporte;
	}
	
	public Long getLicRecibo() {
		return licRecibo;
	}
	
	public void setLicRecibo(Long licRecibo) {
		this.licRecibo = licRecibo;
	}
	
	public Set getCajas() {
		return this.cajas;
	}

	public void setCajas(Set cajas) {
		this.cajas = cajas;
	}

	public Set getCajas_1() {
		return this.cajas_1;
	}

	public void setCajas_1(Set cajas_1) {
		this.cajas_1 = cajas_1;
	}
	
	

}