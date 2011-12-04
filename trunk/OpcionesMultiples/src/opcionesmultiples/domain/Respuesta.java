package opcionesmultiples.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Respuesta entity. @author MyEclipse Persistence Tools
 */

public class Respuesta implements java.io.Serializable {

	// Fields

	private Long id;
	private Pregunta pregunta;
	private String resDetalle;
	private String resCorrecta;
	private Set detalleExamenMultipleChoices = new HashSet(0);

	// Constructors

	/** default constructor */
	public Respuesta() {
	}

	/** minimal constructor */
	public Respuesta(Long id, Pregunta pregunta, String resDetalle,
			String resCorrecta) {
		this.id = id;
		this.pregunta = pregunta;
		this.resDetalle = resDetalle;
		this.resCorrecta = resCorrecta;
	}

	/** full constructor */
	public Respuesta(Long id, Pregunta pregunta, String resDetalle,
			String resCorrecta, Set detalleExamenMultipleChoices) {
		this.id = id;
		this.pregunta = pregunta;
		this.resDetalle = resDetalle;
		this.resCorrecta = resCorrecta;
		this.detalleExamenMultipleChoices = detalleExamenMultipleChoices;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pregunta getPregunta() {
		return this.pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
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

	public Set getDetalleExamenMultipleChoices() {
		return this.detalleExamenMultipleChoices;
	}

	public void setDetalleExamenMultipleChoices(Set detalleExamenMultipleChoices) {
		this.detalleExamenMultipleChoices = detalleExamenMultipleChoices;
	}

}