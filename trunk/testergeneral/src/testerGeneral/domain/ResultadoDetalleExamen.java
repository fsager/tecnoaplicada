package testerGeneral.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * ResultadoDetalleExamen entity. @author MyEclipse Persistence Tools
 */

public class ResultadoDetalleExamen implements java.io.Serializable {

	// Fields

	private Long rdeId;
	private PersonaExamen personaExamen;
	private ExamenDetalle examenDetalle;
	private String rdeResultado;
	private Double rdeNota;
	private Double rdeNota2;
	private Set resultados = new HashSet(0);

	// Constructors

	/** default constructor */
	public ResultadoDetalleExamen() {
	}

	/** minimal constructor */
	public ResultadoDetalleExamen(PersonaExamen personaExamen,
			ExamenDetalle examenDetalle) {
		this.personaExamen = personaExamen;
		this.examenDetalle = examenDetalle;
	}

	/** full constructor */
	public ResultadoDetalleExamen(PersonaExamen personaExamen,
			ExamenDetalle examenDetalle, String rdeResultado, Double rdeNota,
			Set resultados) {
		this.personaExamen = personaExamen;
		this.examenDetalle = examenDetalle;
		this.rdeResultado = rdeResultado;
		this.rdeNota = rdeNota;
		this.resultados = resultados;
	}

	// Property accessors

	public Long getRdeId() {
		return this.rdeId;
	}

	public void setRdeId(Long rdeId) {
		this.rdeId = rdeId;
	}

	public PersonaExamen getPersonaExamen() {
		return this.personaExamen;
	}

	public void setPersonaExamen(PersonaExamen personaExamen) {
		this.personaExamen = personaExamen;
	}

	public ExamenDetalle getExamenDetalle() {
		return this.examenDetalle;
	}

	public void setExamenDetalle(ExamenDetalle examenDetalle) {
		this.examenDetalle = examenDetalle;
	}

	public String getRdeResultado() {
		return this.rdeResultado;
	}

	public void setRdeResultado(String rdeResultado) {
		this.rdeResultado = rdeResultado;
	}

	public Double getRdeNota() {
		return this.rdeNota;
	}

	public void setRdeNota(Double rdeNota) {
		this.rdeNota = rdeNota;
	}
	
	public Double getRdeNota2() {
		return rdeNota2;
	}

	public void setRdeNota2(Double rdeNota2) {
		this.rdeNota2 = rdeNota2;
	}

	public Set getResultados() {
		return this.resultados;
	}

	public void setResultados(Set resultados) {
		this.resultados = resultados;
	}

}