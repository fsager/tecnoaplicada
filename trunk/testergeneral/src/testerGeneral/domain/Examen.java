package testerGeneral.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Examen entity. @author MyEclipse Persistence Tools
 */

public class Examen implements java.io.Serializable {

	// Fields
	public static final String RESULTADO_DENTRO="DENTRO DE LOS PARAMETROS";
	public static final String RESULTADO_FUERA="FUERA DE LOS PARAMETROS";

	
	public static final String ESTADO_INI="INICIADO";
	public static final String ESTADO_FIN="FINALIZADO";
	
	public static final String EXA_CODIGO_VISION="EXA_VISION";
	public static final String EXA_CODIGO_AUDICION="EXA_AUDICION";
	public static final String EXA_CODIGO_PSICOMETRICO="EXA_PSICOMETRICO";
	public static final String EXA_CODIGO_PERSONALIDAD="EXA_PERSONALIDAD";
	
	private Long exaId;
	private String exaCodigo;
	private String exaNombre;
	private Set usuarioExamens = new HashSet(0);
	private Set examenDetalles = new HashSet(0);

	// Constructors

	/** default constructor */
	public Examen() {
	}

	/** minimal constructor */
	public Examen(String exaCodigo, String exaNombre) {
		this.exaCodigo = exaCodigo;
		this.exaNombre = exaNombre;
	}

	/** full constructor */
	public Examen(String exaCodigo, String exaNombre, Set usuarioExamens) {
		this.exaCodigo = exaCodigo;
		this.exaNombre = exaNombre;
		this.usuarioExamens = usuarioExamens;
	}

	// Property accessors

	public Long getExaId() {
		return this.exaId;
	}

	public void setExaId(Long exaId) {
		this.exaId = exaId;
	}

	public String getExaCodigo() {
		return this.exaCodigo;
	}

	public void setExaCodigo(String exaCodigo) {
		this.exaCodigo = exaCodigo;
	}

	public String getExaNombre() {
		return this.exaNombre;
	}

	public void setExaNombre(String exaNombre) {
		this.exaNombre = exaNombre;
	}

	public Set getUsuarioExamens() {
		return this.usuarioExamens;
	}

	public void setUsuarioExamens(Set usuarioExamens) {
		this.usuarioExamens = usuarioExamens;
	}

	public Set getExamenDetalles() {
		return examenDetalles;
	}

	public void setExamenDetalles(Set examenDetalles) {
		this.examenDetalles = examenDetalles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exaId == null) ? 0 : exaId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Examen other = (Examen) obj;
		if (exaId == null) {
			if (other.exaId != null)
				return false;
		} else if (!exaId.equals(other.exaId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return exaNombre;
	}
}