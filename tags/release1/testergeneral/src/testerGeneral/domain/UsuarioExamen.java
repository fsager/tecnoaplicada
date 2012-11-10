package testerGeneral.domain;

/**
 * UsuarioExamen entity. @author MyEclipse Persistence Tools
 */

public class UsuarioExamen implements java.io.Serializable {

	// Fields

	private Long id;
	private Examen examen;
	private Usuario usuario;

	// Constructors

	/** default constructor */
	public UsuarioExamen() {
	}

	/** full constructor */
	public UsuarioExamen(Examen examen, Usuario usuario) {
		this.examen = examen;
		this.usuario = usuario;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Examen getExamen() {
		return this.examen;
	}

	public void setExamen(Examen examen) {
		this.examen = examen;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((examen == null) ? 0 : examen.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		UsuarioExamen other = (UsuarioExamen) obj;
		if (examen == null) {
			if (other.examen != null)
				return false;
		} else if (!examen.equals(other.examen))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
	
		return examen.toString();
	}	

}