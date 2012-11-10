package autoimpresor.domain.access;


/**
 * Licencias entity. @author MyEclipse Persistence Tools
 */

public class Licencias implements java.io.Serializable {

	// Fields

	private Integer codigoLicencia;
	private String observaciones;
	private String clase;
	private String tramite;
	private java.util.Date fechaOtor;
	private java.util.Date fechaVen;
	private String estado;
	private Integer firma;
	private String teorico;
	private String practico;
	private Integer antecedentes;
	private Boolean deuda;
	private Integer responsable;
	private java.util.Date fechaReal;
	private Boolean requisitos;
	private Integer numero;
	private String nroLicencia;
	private Integer confecciono;
	private Integer persona;
	private String medico;
	private String oftalmologico;
	private String psicofisico;
	private String maximo;

	// Constructors

	/** default constructor */
	public Licencias() {
	}

	/** full constructor */
	public Licencias(String observaciones, String clase, String tramite,
			java.util.Date fechaOtor, java.util.Date fechaVen, String estado,
			Integer firma, String teorico, String practico,
			Integer antecedentes, Boolean deuda, Integer responsable,
			java.util.Date fechaReal, Boolean requisitos, Integer numero,
			String nroLicencia, Integer confecciono, Integer persona,
			String medico, String oftalmologico, String psicofisico,
			String maximo) {
		this.observaciones = observaciones;
		this.clase = clase;
		this.tramite = tramite;
		this.fechaOtor = fechaOtor;
		this.fechaVen = fechaVen;
		this.estado = estado;
		this.firma = firma;
		this.teorico = teorico;
		this.practico = practico;
		this.antecedentes = antecedentes;
		this.deuda = deuda;
		this.responsable = responsable;
		this.fechaReal = fechaReal;
		this.requisitos = requisitos;
		this.numero = numero;
		this.nroLicencia = nroLicencia;
		this.confecciono = confecciono;
		this.persona = persona;
		this.medico = medico;
		this.oftalmologico = oftalmologico;
		this.psicofisico = psicofisico;
		this.maximo = maximo;
	}

	// Property accessors

	public Integer getCodigoLicencia() {
		return this.codigoLicencia;
	}

	public void setCodigoLicencia(Integer codigoLicencia) {
		this.codigoLicencia = codigoLicencia;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getClase() {
		return this.clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public String getTramite() {
		return this.tramite;
	}

	public void setTramite(String tramite) {
		this.tramite = tramite;
	}

	public java.util.Date getFechaOtor() {
		return this.fechaOtor;
	}

	public void setFechaOtor(java.util.Date fechaOtor) {
		this.fechaOtor = fechaOtor;
	}

	public java.util.Date getFechaVen() {
		return this.fechaVen;
	}

	public void setFechaVen(java.util.Date fechaVen) {
		this.fechaVen = fechaVen;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getFirma() {
		return this.firma;
	}

	public void setFirma(Integer firma) {
		this.firma = firma;
	}

	public String getTeorico() {
		return this.teorico;
	}

	public void setTeorico(String teorico) {
		this.teorico = teorico;
	}

	public String getPractico() {
		return this.practico;
	}

	public void setPractico(String practico) {
		this.practico = practico;
	}

	public Integer getAntecedentes() {
		return this.antecedentes;
	}

	public void setAntecedentes(Integer antecedentes) {
		this.antecedentes = antecedentes;
	}

	public Boolean getDeuda() {
		return this.deuda;
	}

	public void setDeuda(Boolean deuda) {
		this.deuda = deuda;
	}

	public Integer getResponsable() {
		return this.responsable;
	}

	public void setResponsable(Integer responsable) {
		this.responsable = responsable;
	}

	public java.util.Date getFechaReal() {
		return this.fechaReal;
	}

	public void setFechaReal(java.util.Date fechaReal) {
		this.fechaReal = fechaReal;
	}

	public Boolean getRequisitos() {
		return this.requisitos;
	}

	public void setRequisitos(Boolean requisitos) {
		this.requisitos = requisitos;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getNroLicencia() {
		return this.nroLicencia;
	}

	public void setNroLicencia(String nroLicencia) {
		this.nroLicencia = nroLicencia;
	}

	public Integer getConfecciono() {
		return this.confecciono;
	}

	public void setConfecciono(Integer confecciono) {
		this.confecciono = confecciono;
	}

	public Integer getPersona() {
		return this.persona;
	}

	public void setPersona(Integer persona) {
		this.persona = persona;
	}

	public String getMedico() {
		return this.medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}

	public String getOftalmologico() {
		return this.oftalmologico;
	}

	public void setOftalmologico(String oftalmologico) {
		this.oftalmologico = oftalmologico;
	}

	public String getPsicofisico() {
		return this.psicofisico;
	}

	public void setPsicofisico(String psicofisico) {
		this.psicofisico = psicofisico;
	}

	public String getMaximo() {
		return this.maximo;
	}

	public void setMaximo(String maximo) {
		this.maximo = maximo;
	}

}