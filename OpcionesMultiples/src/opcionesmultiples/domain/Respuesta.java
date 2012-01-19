package opcionesmultiples.domain;

import java.util.HashSet;
import java.util.Set;

import testerGeneral.domain.PreguntaInterfaz;
import testerGeneral.domain.RespuestaInterfaz;

/**
 * Respuesta entity. @author MyEclipse Persistence Tools
 */

public class Respuesta implements java.io.Serializable,RespuestaInterfaz {

	// Fields

	private Long id;
	private PreguntaInterfaz pregunta;
	private String resDetalle;
	private String resCorrecta;

	// Constructors

	/** default constructor */
	public Respuesta() {
	}


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PreguntaInterfaz getPregunta() {
		return this.pregunta;
	}

	public void setPregunta(PreguntaInterfaz pregunta) {
		this.pregunta = pregunta;
	}

	public String getResDetalle() {
		return this.resDetalle;
	}

	public void setResDetalle(String resDetalle) {
		this.resDetalle = resDetalle;
	}

	public String getResCorrecta() {
		return this.resCorrecta;
	}

	public void setResCorrecta(String resCorrecta) {
		this.resCorrecta = resCorrecta;
	}
}