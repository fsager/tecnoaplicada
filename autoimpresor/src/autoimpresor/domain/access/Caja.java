package autoimpresor.domain.access;

/**
 * Caja entity. @author MyEclipse Persistence Tools
 */

public class Caja implements java.io.Serializable {

	// Fields

	private Integer importe;
	private Integer licencia;
	private String observaciones;
	private Integer recibo;
	private java.util.Date fecha;

	// Constructors

	/** default constructor */
	public Caja() {
	}

	/** full constructor */
	public Caja(Integer importe, Integer licencia, String observaciones,
			Integer recibo, java.util.Date fecha) {
		this.importe = importe;
		this.licencia = licencia;
		this.observaciones = observaciones;
		this.recibo = recibo;
		this.fecha = fecha;
	}

	// Property accessors

	public Integer getImporte() {
		return this.importe;
	}

	public void setImporte(Integer importe) {
		this.importe = importe;
	}

	public Integer getLicencia() {
		return this.licencia;
	}

	public void setLicencia(Integer licencia) {
		this.licencia = licencia;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Integer getRecibo() {
		return this.recibo;
	}

	public void setRecibo(Integer recibo) {
		this.recibo = recibo;
	}

	public java.util.Date getFecha() {
		return this.fecha;
	}

	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Caja))
			return false;
		Caja castOther = (Caja) other;

		return ((this.getImporte() == castOther.getImporte()) || (this
				.getImporte() != null
				&& castOther.getImporte() != null && this.getImporte().equals(
				castOther.getImporte())))
				&& ((this.getLicencia() == castOther.getLicencia()) || (this
						.getLicencia() != null
						&& castOther.getLicencia() != null && this
						.getLicencia().equals(castOther.getLicencia())))
				&& ((this.getObservaciones() == castOther.getObservaciones()) || (this
						.getObservaciones() != null
						&& castOther.getObservaciones() != null && this
						.getObservaciones()
						.equals(castOther.getObservaciones())))
				&& ((this.getRecibo() == castOther.getRecibo()) || (this
						.getRecibo() != null
						&& castOther.getRecibo() != null && this.getRecibo()
						.equals(castOther.getRecibo())))
				&& ((this.getFecha() == castOther.getFecha()) || (this
						.getFecha() != null
						&& castOther.getFecha() != null && this.getFecha()
						.equals(castOther.getFecha())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getImporte() == null ? 0 : this.getImporte().hashCode());
		result = 37 * result
				+ (getLicencia() == null ? 0 : this.getLicencia().hashCode());
		result = 37
				* result
				+ (getObservaciones() == null ? 0 : this.getObservaciones()
						.hashCode());
		result = 37 * result
				+ (getRecibo() == null ? 0 : this.getRecibo().hashCode());
		result = 37 * result
				+ (getFecha() == null ? 0 : this.getFecha().hashCode());
		return result;
	}

}