package autoimpresor.domain;

/**
 * Antecedente entity. @author MyEclipse Persistence Tools
 */

public class Antecedente implements java.io.Serializable {

	// Fields

	private Long antId;
	private String antDescripcion;
	private String antApruebaSn;

	// Constructors

	/** default constructor */
	public Antecedente() {
	}

	/** full constructor */
	public Antecedente(String antDescripcion, String antApruebaSn) {
		this.antDescripcion = antDescripcion;
		this.antApruebaSn = antApruebaSn;
	}

	// Property accessors

	public Long getAntId() {
		return this.antId;
	}

	public void setAntId(Long antId) {
		this.antId = antId;
	}

	public String getAntDescripcion() {
		return this.antDescripcion;
	}

	public void setAntDescripcion(String antDescripcion) {
		this.antDescripcion = antDescripcion;
	}

	public String getAntApruebaSn() {
		return this.antApruebaSn;
	}

	public void setAntApruebaSn(String antApruebaSn) {
		this.antApruebaSn = antApruebaSn;
	}

	public String toString() {
		return this.getAntDescripcion();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((antId == null) ? 0 : antId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Antecedente other = (Antecedente) obj;
		if (antId == null) {
			if (other.antId != null)
				return false;
		} else if (!antId.equals(other.antId))
			return false;
		return true;
	}


}
