package testerGeneral.domain;

/**
 * Propiedad entity. @author MyEclipse Persistence Tools
 */

public class Propiedad implements java.io.Serializable {

	// Fields

	private String propClave;
	private String propValor;
	private byte propBlob[]=new byte[1];

	// Constructors

	/** default constructor */
	public Propiedad() {
	}

	/** full constructor */
	public Propiedad(String propValor) {
		this.propValor = propValor;
	}

	// Property accessors

	public String getPropClave() {
		return this.propClave;
	}

	public void setPropClave(String propClave) {
		this.propClave = propClave;
	}

	public String getPropValor() {
		return this.propValor;
	}

	public void setPropValor(String propValor) {
		this.propValor = propValor;
	}
	
	public void setPropBlob(byte[] propBlob) {
		this.propBlob = propBlob;
	}
	
	public byte[] getPropBlob() {
		return propBlob;
	}
}