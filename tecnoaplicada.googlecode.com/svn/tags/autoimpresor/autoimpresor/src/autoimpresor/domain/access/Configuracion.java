package autoimpresor.domain.access;

/**
 * Configuracion entity. @author MyEclipse Persistence Tools
 */

public class Configuracion implements java.io.Serializable {

	// Fields

	private Integer codigoConfiguracion;
	private String item;
	private String valor;

	// Constructors

	/** default constructor */
	public Configuracion() {
	}

	/** full constructor */
	public Configuracion(String item, String valor) {
		this.item = item;
		this.valor = valor;
	}

	// Property accessors

	public Integer getCodigoConfiguracion() {
		return this.codigoConfiguracion;
	}

	public void setCodigoConfiguracion(Integer codigoConfiguracion) {
		this.codigoConfiguracion = codigoConfiguracion;
	}

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}