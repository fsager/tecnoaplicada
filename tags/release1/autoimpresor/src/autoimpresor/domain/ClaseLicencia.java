package autoimpresor.domain;

/**
 * ClaseLicencia entity. @author MyEclipse Persistence Tools
 */

public class ClaseLicencia implements java.io.Serializable {

	// Fields

	private Long cllId;
	private String cllNombreClase;
	private Long cllVigenciaPredeterminada;
	private Long cllEdadMinima;
	private Long cllEdadMaxima;
	private String cllDescripcionCorta;
	private String cllDescripcion;
	private Double cllImportex6meses;
	private Double cllImportex12meses;
	private Double cllImportex24meses;
	private Double cllImportex36meses;
	private Double cllImportex48meses;
	private Double cllImportex60meses;

	// Constructors

	/** default constructor */
	public ClaseLicencia() {
	}

	/** minimal constructor */
	public ClaseLicencia(String cllNombreClase, Long cllVigenciaPredeterminada,
			Long cllEdadMinima, Long cllEdadMaxima, String cllDescripcionCorta,
			String cllDescripcion) {
		this.cllNombreClase = cllNombreClase;
		this.cllVigenciaPredeterminada = cllVigenciaPredeterminada;
		this.cllEdadMinima = cllEdadMinima;
		this.cllEdadMaxima = cllEdadMaxima;
		this.cllDescripcionCorta = cllDescripcionCorta;
		this.cllDescripcion = cllDescripcion;
	}

	/** full constructor */
	public ClaseLicencia(String cllNombreClase, Long cllVigenciaPredeterminada,
			Long cllEdadMinima, Long cllEdadMaxima, String cllDescripcionCorta,
			String cllDescripcion, Double cllImportex6meses,
			Double cllImportex12meses, Double cllImportex24meses,
			Double cllImportex36meses, Double cllImportex48meses,
			Double cllImportex60meses) {
		this.cllNombreClase = cllNombreClase;
		this.cllVigenciaPredeterminada = cllVigenciaPredeterminada;
		this.cllEdadMinima = cllEdadMinima;
		this.cllEdadMaxima = cllEdadMaxima;
		this.cllDescripcionCorta = cllDescripcionCorta;
		this.cllDescripcion = cllDescripcion;
		this.cllImportex6meses = cllImportex6meses;
		this.cllImportex12meses = cllImportex12meses;
		this.cllImportex24meses = cllImportex24meses;
		this.cllImportex36meses = cllImportex36meses;
		this.cllImportex48meses = cllImportex48meses;
		this.cllImportex60meses = cllImportex60meses;
	}

	// Property accessors

	public Long getCllId() {
		return this.cllId;
	}

	public void setCllId(Long cllId) {
		this.cllId = cllId;
	}

	public String getCllNombreClase() {
		return this.cllNombreClase;
	}

	public void setCllNombreClase(String cllNombreClase) {
		this.cllNombreClase = cllNombreClase;
	}

	public Long getCllVigenciaPredeterminada() {
		return this.cllVigenciaPredeterminada;
	}

	public void setCllVigenciaPredeterminada(Long cllVigenciaPredeterminada) {
		this.cllVigenciaPredeterminada = cllVigenciaPredeterminada;
	}

	public Long getCllEdadMinima() {
		return this.cllEdadMinima;
	}

	public void setCllEdadMinima(Long cllEdadMinima) {
		this.cllEdadMinima = cllEdadMinima;
	}

	public Long getCllEdadMaxima() {
		return this.cllEdadMaxima;
	}

	public void setCllEdadMaxima(Long cllEdadMaxima) {
		this.cllEdadMaxima = cllEdadMaxima;
	}

	public String getCllDescripcionCorta() {
		return this.cllDescripcionCorta;
	}

	public void setCllDescripcionCorta(String cllDescripcionCorta) {
		this.cllDescripcionCorta = cllDescripcionCorta;
	}

	public String getCllDescripcion() {
		return this.cllDescripcion;
	}

	public void setCllDescripcion(String cllDescripcion) {
		this.cllDescripcion = cllDescripcion;
	}

	public Double getCllImportex6meses() {
		return this.cllImportex6meses;
	}

	public void setCllImportex6meses(Double cllImportex6meses) {
		this.cllImportex6meses = cllImportex6meses;
	}

	public Double getCllImportex12meses() {
		return this.cllImportex12meses;
	}

	public void setCllImportex12meses(Double cllImportex12meses) {
		this.cllImportex12meses = cllImportex12meses;
	}

	public Double getCllImportex24meses() {
		return this.cllImportex24meses;
	}

	public void setCllImportex24meses(Double cllImportex24meses) {
		this.cllImportex24meses = cllImportex24meses;
	}

	public Double getCllImportex36meses() {
		return this.cllImportex36meses;
	}

	public void setCllImportex36meses(Double cllImportex36meses) {
		this.cllImportex36meses = cllImportex36meses;
	}

	public Double getCllImportex48meses() {
		return this.cllImportex48meses;
	}

	public void setCllImportex48meses(Double cllImportex48meses) {
		this.cllImportex48meses = cllImportex48meses;
	}

	public Double getCllImportex60meses() {
		return this.cllImportex60meses;
	}

	public void setCllImportex60meses(Double cllImportex60meses) {
		this.cllImportex60meses = cllImportex60meses;
	}

	public String toString () {
		if(this.cllNombreClase==null)
			return "";
		
		return this.getCllNombreClase() + " ("+this.getCllDescripcionCorta()+")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cllId == null) ? 0 : cllId.hashCode());
		result = prime * result
				+ ((cllNombreClase == null) ? 0 : cllNombreClase.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		
		ClaseLicencia other = (ClaseLicencia) obj;
		if (cllId == null) {
			if (other.cllId != null)
				return false;
		} else if (!cllId.equals(other.cllId))
			return false;
		if (cllNombreClase == null) {
			if (other.cllNombreClase != null)
				return false;
		} else if (!cllNombreClase.equals(other.cllNombreClase))
			return false;
		return true;
	}
	

	
}