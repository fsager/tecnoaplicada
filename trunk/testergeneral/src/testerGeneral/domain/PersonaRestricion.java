package testerGeneral.domain;

/**
 * PersonaRestricion entity. @author MyEclipse Persistence Tools
 */

public class PersonaRestricion implements java.io.Serializable {

	// Fields

	private Long id;
	private Persona persona;
	private String descripcion;
	private Long domId;

	// Constructors

	/** default constructor */
	public PersonaRestricion() {
	}

	/** full constructor */
	public PersonaRestricion(Persona persona, String descripcion) {
		this.persona = persona;
		this.descripcion = descripcion;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getDomId() {
		return domId;
	}

	public void setDomId(Long domId) {
		this.domId = domId;
	}

	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;		
		result = prime * result + ((domId == null) ? 0 : domId.hashCode());
		result = prime * result + ((persona == null) ? 0 : persona.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		PersonaRestricion other = (PersonaRestricion) obj;
		if (domId == null) {
			if (other.domId != null)
				return false;
		} else if (!domId.equals(other.domId))
			return false;
		if (persona == null) {
			if (other.persona != null)
				return false;
		} else if (!persona.equals(other.persona))
			return false;
		return true;
	}

	@Override
	public String toString() {	
		return descripcion;
	}
}