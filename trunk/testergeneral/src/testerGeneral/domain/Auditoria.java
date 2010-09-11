package testerGeneral.domain;

import java.util.Date;

/**
 * Auditoria entity. @author MyEclipse Persistence Tools
 */

public class Auditoria implements java.io.Serializable {

	// Fields

	private Long audId;
	private String audAccion;
	private String audObjeto;
	private String audFk;
	private Date audFecha;
	private String audUsuario;
	private String audEstacion;
	
	// Constructors

	/** default constructor */
	public Auditoria() {
	}

	// Property accessors

	public Long getAudId() {
		return this.audId;
	}

	public void setAudId(Long audId) {
		this.audId = audId;
	}

	public String getAudAccion() {
		return this.audAccion;
	}

	public void setAudAccion(String audAccion) {
		this.audAccion = audAccion;
	}

	public String getAudObjeto() {
		return this.audObjeto;
	}

	public void setAudObjeto(String audObjeto) {
		this.audObjeto = audObjeto;
	}

	public Date getAudFecha() {
		return this.audFecha;
	}

	public void setAudFecha(Date audFecha) {
		this.audFecha = audFecha;
	}

	public String getAudUsuario() {
		return this.audUsuario;
	}

	public void setAudUsuario(String audUsuario) {
		this.audUsuario = audUsuario;
	}

	public String getAudFk() {
		return audFk;
	}

	public void setAudFk(String audFk) {
		this.audFk = audFk;
	}

	public String getAudEstacion() {
		return audEstacion;
	}

	public void setAudEstacion(String audEstacion) {
		this.audEstacion = audEstacion;
	}

}