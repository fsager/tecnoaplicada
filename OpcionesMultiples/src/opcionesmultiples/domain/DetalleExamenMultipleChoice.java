package opcionesmultiples.domain;

import testerGeneral.domain.DetalleExamenMultipleChoiceInterfaz;
import testerGeneral.domain.PreguntaInterfaz;
import testerGeneral.domain.RespuestaInterfaz;
import testerGeneral.domain.PersonaExamen;



/**
 * DetalleExamenMultipleChoice entity. @author MyEclipse Persistence Tools
 */

public class DetalleExamenMultipleChoice implements java.io.Serializable,DetalleExamenMultipleChoiceInterfaz {

	// Fields

	private Long id;
	private PersonaExamen personaExamen;
	private RespuestaInterfaz respuesta;
	private PreguntaInterfaz pregunta;
	private String preDetalle;
	private String resDetalle;
	private String resCorrecta;

	// Constructors

	/** default constructor */
	public DetalleExamenMultipleChoice() {
	}

	/** full constructor */
	public DetalleExamenMultipleChoice(Long id, PersonaExamen personaExamen,
			Respuesta respuesta, Pregunta pregunta, String preDetalle,
			String resDetalle, String resCorrecta) {
		this.id = id;
		this.personaExamen = personaExamen;
		this.respuesta = respuesta;
		this.pregunta = pregunta;
		this.preDetalle = preDetalle;
		this.resDetalle = resDetalle;
		this.resCorrecta = resCorrecta;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonaExamen getPersonaExamen() {
		return this.personaExamen;
	}

	public void setPersonaExamen(PersonaExamen personaExamen) {
		this.personaExamen = personaExamen;
	}

	public RespuestaInterfaz getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(RespuestaInterfaz respuesta) {
		this.respuesta = respuesta;
	}

	public PreguntaInterfaz getPregunta() {
		return this.pregunta;
	}

	public void setPregunta(PreguntaInterfaz pregunta) {
		this.pregunta = pregunta;
	}

	public String getPreDetalle() {
		return this.preDetalle;
	}

	public void setPreDetalle(String preDetalle) {
		this.preDetalle = preDetalle;
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