package autoimpresor.domain.access;

/**
 * Localidades entity. @author MyEclipse Persistence Tools
 */

public class Localidades implements java.io.Serializable {

	// Fields

	private Integer codigoLocalidad;
	private String descripcionLocalidad;
	private String codigoMunicipal;

	// Constructors

	/** default constructor */
	public Localidades() {
	}

	/** full constructor */
	public Localidades(String descripcionLocalidad, String codigoMunicipal) {
		this.descripcionLocalidad = descripcionLocalidad;
		this.codigoMunicipal = codigoMunicipal;
	}

	// Property accessors

	public Integer getCodigoLocalidad() {
		return this.codigoLocalidad;
	}

	public void setCodigoLocalidad(Integer codigoLocalidad) {
		this.codigoLocalidad = codigoLocalidad;
	}

	public String getDescripcionLocalidad() {
		return this.descripcionLocalidad;
	}

	public void setDescripcionLocalidad(String descripcionLocalidad) {
		this.descripcionLocalidad = descripcionLocalidad;
	}

	public String getCodigoMunicipal() {
		return this.codigoMunicipal;
	}

	public void setCodigoMunicipal(String codigoMunicipal) {
		this.codigoMunicipal = codigoMunicipal;
	}

}