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
	private String rdeDetalleResultado;
	private Double rdeNota;
	private Double rdeNota2;
	private Double rdeNota3;
	private Double rdeNota4;
	private String rdeParametrosCorrecion;
	private byte rdeImagen[] = new byte[1];
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

	public void setRdeNota3(Double rdeNota3) {
		this.rdeNota3 = rdeNota3;
	}
	
	public Double getRdeNota3() {
		return rdeNota3;
	}

	public void setRdeNota4(Double rdeNota4) {
		this.rdeNota4 = rdeNota4;
	}
	
	public Double getRdeNota4() {
		return rdeNota4;
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
	
	public String getRdeDetalleResultado() {
		return rdeDetalleResultado;
	}
	
	public void setRdeDetalleResultado(String rdeDetalleResultado) {
		this.rdeDetalleResultado = rdeDetalleResultado;
	}
	
	public String getRdeParametrosCorrecion() {
		return rdeParametrosCorrecion;
	}
	
	public void setRdeParametrosCorrecion(String rdeParametrosCorrecion) {
		this.rdeParametrosCorrecion = rdeParametrosCorrecion;
	}
	
	public byte[] getRdeImagen() {
		return rdeImagen;
	}
	
	public void setRdeImagen(byte[] rdeImagen) {
		this.rdeImagen = rdeImagen;
	}
}