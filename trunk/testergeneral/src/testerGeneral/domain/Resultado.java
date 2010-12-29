package testerGeneral.domain;

/**
 * Resultado entity. @author MyEclipse Persistence Tools
 */

public class Resultado implements java.io.Serializable {

	// Fields

	private Long resId;
	private ResultadoDetalleExamen resultadoDetalleExamen;
	private Long resEtapa;
	private String resEtapaDesc;
	private Double resValor1;
	private Double resValor2;

	// Constructors

	/** default constructor */
	public Resultado() {
	}

	/** minimal constructor */
	public Resultado(ResultadoDetalleExamen resultadoDetalleExamen) {
		this.resultadoDetalleExamen = resultadoDetalleExamen;
	}

	/** full constructor */
	public Resultado(ResultadoDetalleExamen resultadoDetalleExamen,
			Long resEtapa, Double resValor1, Double resValor2) {
		this.resultadoDetalleExamen = resultadoDetalleExamen;
		this.resEtapa = resEtapa;
		this.resValor1 = resValor1;
		this.resValor2 = resValor2;
	}

	// Property accessors

	public Long getResId() {
		return this.resId;
	}

	public void setResId(Long resId) {
		this.resId = resId;
	}

	public ResultadoDetalleExamen getResultadoDetalleExamen() {
		return this.resultadoDetalleExamen;
	}

	public void setResultadoDetalleExamen(
			ResultadoDetalleExamen resultadoDetalleExamen) {
		this.resultadoDetalleExamen = resultadoDetalleExamen;
	}

	public Long getResEtapa() {
		return this.resEtapa;
	}

	public void setResEtapa(Long resEtapa) {
		this.resEtapa = resEtapa;
	}

	public Double getResValor1() {
		return this.resValor1;
	}

	public void setResValor1(Double resValor1) {
		this.resValor1 = resValor1;
	}

	public Double getResValor2() {
		return this.resValor2;
	}

	public void setResValor2(Double resValor2) {
		this.resValor2 = resValor2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((resEtapa == null) ? 0 : resEtapa.hashCode());
		result = prime * result + ((resId == null) ? 0 : resId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Resultado other = (Resultado) obj;
		if (resEtapa == null) {
			if (other.resEtapa != null)
				return false;
		} else if (!resEtapa.equals(other.resEtapa))
			return false;
		if (resId == null) {
			if (other.resId != null)
				return false;
		} else if (!resId.equals(other.resId))
			return false;
		return true;
	}

	public String getResEtapaDesc() {
		return resEtapaDesc;
	}
	
	public void setResEtapaDesc(String resEtapaDesc) {
		this.resEtapaDesc = resEtapaDesc;
	}
	
	
}