package testerGeneral.domain;

/**
 * BinaryTableId entity. @author MyEclipse Persistence Tools
 */

public class BinaryTableId implements java.io.Serializable {

	// Fields

	private String perFoto;
	private String perFirma;

	// Constructors

	/** default constructor */
	public BinaryTableId() {
	}

	/** full constructor */
	public BinaryTableId(String perFoto, String perFirma) {
		this.perFoto = perFoto;
		this.perFirma = perFirma;
	}

	// Property accessors

	public String getPerFoto() {
		return this.perFoto;
	}

	public void setPerFoto(String perFoto) {
		this.perFoto = perFoto;
	}

	public String getPerFirma() {
		return this.perFirma;
	}

	public void setPerFirma(String perFirma) {
		this.perFirma = perFirma;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BinaryTableId))
			return false;
		BinaryTableId castOther = (BinaryTableId) other;

		return ((this.getPerFoto() == castOther.getPerFoto()) || (this
				.getPerFoto() != null
				&& castOther.getPerFoto() != null && this.getPerFoto().equals(
				castOther.getPerFoto())))
				&& ((this.getPerFirma() == castOther.getPerFirma()) || (this
						.getPerFirma() != null
						&& castOther.getPerFirma() != null && this
						.getPerFirma().equals(castOther.getPerFirma())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getPerFoto() == null ? 0 : this.getPerFoto().hashCode());
		result = 37 * result
				+ (getPerFirma() == null ? 0 : this.getPerFirma().hashCode());
		return result;
	}

}