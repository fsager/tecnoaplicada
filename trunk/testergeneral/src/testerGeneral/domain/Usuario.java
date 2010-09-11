package testerGeneral.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Usuario entity. @author MyEclipse Persistence Tools
 */

public class Usuario extends UsuarioCommon implements java.io.Serializable {

	// Fields

	private Set usuarioPermisos = new HashSet(0);
	private Set usuarioExamens = new HashSet(0);
	

	// Constructors

	/** default constructor */
	public Usuario() {
	}

	// Property accessors

	

	public Set getUsuarioPermisos() {
		return this.usuarioPermisos;
	}

	public void setUsuarioPermisos(Set usuarioPermisos) {
		this.usuarioPermisos = usuarioPermisos;
	}

	public Set getUsuarioExamens() {
		return this.usuarioExamens;
	}

	public void setUsuarioExamens(Set usuarioExamens) {
		this.usuarioExamens = usuarioExamens;
	}
	
}