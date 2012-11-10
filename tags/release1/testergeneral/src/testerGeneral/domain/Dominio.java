package testerGeneral.domain;

/**
 * Dominio entity. @author MyEclipse Persistence Tools
 */

public class Dominio implements java.io.Serializable {

	// Fields

	private Long domId;
	private String domTipo;
	private String domClave;
	private String domCodigo;
	private String domValorMostrar;
	private String domDescripcion;

	// Constructors

	/** default constructor */
	public Dominio() {
	}

	/** minimal constructor */
	public Dominio(String domTipo, String domClave, String domCodigo,
			String domValorMostrar) {
		this.domTipo = domTipo;
		this.domClave = domClave;
		this.domCodigo = domCodigo;
		this.domValorMostrar = domValorMostrar;
	}

	/** full constructor */
	public Dominio(String domTipo, String domClave, String domCodigo,
			String domValorMostrar, String domDescripcion) {
		this.domTipo = domTipo;
		this.domClave = domClave;
		this.domCodigo = domCodigo;
		this.domValorMostrar = domValorMostrar;
		this.domDescripcion = domDescripcion;
	}

	// Property accessors
	public Long getDomId() {
		return domId;
	}

	public void setDomId(Long domId) {
		this.domId = domId;
	}
	
	public String getDomTipo() {
		return this.domTipo;
	}

	public void setDomTipo(String domTipo) {
		this.domTipo = domTipo;
	}

	public String getDomClave() {
		return this.domClave;
	}

	public void setDomClave(String domClave) {
		this.domClave = domClave;
	}

	public String getDomCodigo() {
		return this.domCodigo;
	}

	public void setDomCodigo(String domCodigo) {
		this.domCodigo = domCodigo;
	}

	public String getDomValorMostrar() {
		return this.domValorMostrar;
	}

	public void setDomValorMostrar(String domValorMostrar) {
		this.domValorMostrar = domValorMostrar;
	}

	public String getDomDescripcion() {
		return this.domDescripcion;
	}

	public void setDomDescripcion(String domDescripcion) {
		this.domDescripcion = domDescripcion;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;

		Dominio castOther = (Dominio) other;

		return ((this.getDomTipo() == castOther.getDomTipo()) || (this
				.getDomTipo() != null
				&& castOther.getDomTipo() != null && this.getDomTipo().equals(
				castOther.getDomTipo())))
				&& ((this.getDomClave() == castOther.getDomClave()) || (this
						.getDomClave() != null
						&& castOther.getDomClave() != null && this
						.getDomClave().equals(castOther.getDomClave())))
				&& ((this.getDomCodigo() == castOther.getDomCodigo()) || (this
						.getDomCodigo() != null
						&& castOther.getDomCodigo() != null && this
						.getDomCodigo().equals(castOther.getDomCodigo())))
				&& ((this.getDomValorMostrar() == castOther
						.getDomValorMostrar()) || (this.getDomValorMostrar() != null
						&& castOther.getDomValorMostrar() != null && this
						.getDomValorMostrar().equals(
								castOther.getDomValorMostrar())))
				&& ((this.getDomDescripcion() == castOther.getDomDescripcion()) || (this
						.getDomDescripcion() != null
						&& castOther.getDomDescripcion() != null && this
						.getDomDescripcion().equals(
								castOther.getDomDescripcion())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getDomTipo() == null ? 0 : this.getDomTipo().hashCode());
		result = 37 * result
				+ (getDomClave() == null ? 0 : this.getDomClave().hashCode());
		result = 37 * result
				+ (getDomCodigo() == null ? 0 : this.getDomCodigo().hashCode());
		result = 37
				* result
				+ (getDomValorMostrar() == null ? 0 : this.getDomValorMostrar()
						.hashCode());
		result = 37
				* result
				+ (getDomDescripcion() == null ? 0 : this.getDomDescripcion()
						.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return domValorMostrar;
	}
}