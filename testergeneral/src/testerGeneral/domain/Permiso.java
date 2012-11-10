package testerGeneral.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Permiso entity. @author MyEclipse Persistence Tools
 */

public class Permiso implements java.io.Serializable {

	// Fields

	private Long perId;
	private String perCodigo;
	private String perDescripcion;
	private Set usuarioPermisos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Permiso() {
	}

	/** minimal constructor */
	public Permiso(String perCodigo, String perDescripcion) {
		this.perCodigo = perCodigo;
		this.perDescripcion = perDescripcion;
	}

	/** full constructor */
	public Permiso(String perCodigo, String perDescripcion, Set usuarioPermisos) {
		this.perCodigo = perCodigo;
		this.perDescripcion = perDescripcion;
		this.usuarioPermisos = usuarioPermisos;
	}

	// Property accessors

	public Long getPerId() {
		return this.perId;
	}

	public void setPerId(Long perId) {
		this.perId = perId;
	}

	public String getPerCodigo() {
		return this.perCodigo;
	}

	public void setPerCodigo(String perCodigo) {
		this.perCodigo = perCodigo;
	}

	public String getPerDescripcion() {
		return this.perDescripcion;
	}

	public void setPerDescripcion(String perDescripcion) {
		this.perDescripcion = perDescripcion;
	}

	public Set getUsuarioPermisos() {
		return this.usuarioPermisos;
	}

	public void setUsuarioPermisos(Set usuarioPermisos) {
		this.usuarioPermisos = usuarioPermisos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((perId == null) ? 0 : perId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permiso other = (Permiso) obj;
		if (perId == null) {
			if (other.perId != null)
				return false;
		} else if (!perId.equals(other.perId))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return perDescripcion;
	}

}