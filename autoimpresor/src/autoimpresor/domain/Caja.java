package autoimpresor.domain;

import java.util.Date;

/**
 * Caja entity. @author MyEclipse Persistence Tools
 */

public class Caja implements java.io.Serializable {

	// Fields

	private Long cajId;
	private Licencia licencia;
	private Double cajImporte;
	private String cajObservaciones;
	private Long cajRecibo;
	private Date cajFecha;

	// Constructors

	/** default constructor */
	public Caja() {
	}

	/** minimal constructor */
	public Caja(Licencia licencia, Long cajRecibo, Date cajFecha) {
		this.licencia = licencia;
		this.cajRecibo = cajRecibo;
		this.cajFecha = cajFecha;
	}

	/** full constructor */
	public Caja(Licencia licencia, Double cajImporte, String cajObservaciones,
			Long cajRecibo, Date cajFecha) {
		this.licencia = licencia;
		this.cajImporte = cajImporte;
		this.cajObservaciones = cajObservaciones;
		this.cajRecibo = cajRecibo;
		this.cajFecha = cajFecha;
	}

	// Property accessors

	public Long getCajId() {
		return this.cajId;
	}

	public void setCajId(Long cajId) {
		this.cajId = cajId;
	}

	public Licencia getLicencia() {
		return this.licencia;
	}

	public void setLicencia(Licencia licencia) {
		this.licencia = licencia;
	}

	public Double getCajImporte() {
		return this.cajImporte;
	}

	public void setCajImporte(Double cajImporte) {
		this.cajImporte = cajImporte;
	}

	public String getCajObservaciones() {
		return this.cajObservaciones;
	}

	public void setCajObservaciones(String cajObservaciones) {
		this.cajObservaciones = cajObservaciones;
	}

	public Long getCajRecibo() {
		return this.cajRecibo;
	}

	public void setCajRecibo(Long cajRecibo) {
		this.cajRecibo = cajRecibo;
	}

	public java.util.Date getCajFecha() {
		return this.cajFecha;
	}

	public void setCajFecha(java.util.Date cajFecha) {
		this.cajFecha = cajFecha;
	}

}