package autoimpresor.domain.access;

/**
 * Antecedentes entity. @author MyEclipse Persistence Tools
 */

public class Antecedentes implements java.io.Serializable {

	// Fields

	private Integer codigoAntecedente;
	private String descripcionAntecedente;
	private String aprueba;

	// Constructors

	/** default constructor */
	public Antecedentes() {
	}

	/** full constructor */
	public Antecedentes(String descripcionAntecedente, String aprueba) {
		this.descripcionAntecedente = descripcionAntecedente;
		this.aprueba = aprueba;
	}

	// Property accessors

	public Integer getCodigoAntecedente() {
		return this.codigoAntecedente;
	}

	public void setCodigoAntecedente(Integer codigoAntecedente) {
		this.codigoAntecedente = codigoAntecedente;
	}

	public String getDescripcionAntecedente() {
		return this.descripcionAntecedente;
	}

	public void setDescripcionAntecedente(String descripcionAntecedente) {
		this.descripcionAntecedente = descripcionAntecedente;
	}

	public String getAprueba() {
		return this.aprueba;
	}

	public void setAprueba(String aprueba) {
		this.aprueba = aprueba;
	}

}