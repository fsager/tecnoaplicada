package opcionesmultiples.domain;

import java.util.HashSet;
import java.util.Set;

import testerGeneral.domain.PreguntaInterfaz;

/**
 * Pregunta entity. @author MyEclipse Persistence Tools
 */

public class Pregunta implements java.io.Serializable,PreguntaInterfaz {

	// Fields

	private Long id;
	private String preDetalle;
	private String preCat;
	private Long preValoracion;
	private Long preOrden;
	private byte preImagen[]=new byte[1];
	private Set detalleExamenMultipleChoices = new HashSet(0);
	private Set respuestas = new HashSet(0);

	// Constructors

	/** default constructor */
	public Pregunta() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPreDetalle() {
		return this.preDetalle;
	}

	public void setPreDetalle(String preDetalle) {
		this.preDetalle = preDetalle;
	}

	public String getPreCat() {
		return this.preCat;
	}

	public void setPreCat(String preCat) {
		this.preCat = preCat;
	}

	public Long getPreValoracion() {
		return this.preValoracion;
	}

	public void setPreValoracion(Long preValoracion) {
		this.preValoracion = preValoracion;
	}

	public Long getPreOrden() {
		return this.preOrden;
	}

	public void setPreOrden(Long preOrden) {
		this.preOrden = preOrden;
	}

	public byte[] getPreImagen() {
		return preImagen;
	}
	
	public void setPreImagen(byte[] preImagen) {
		this.preImagen = preImagen;
	}
	
	public Set getDetalleExamenMultipleChoices() {
		return this.detalleExamenMultipleChoices;
	}

	public void setDetalleExamenMultipleChoices(Set detalleExamenMultipleChoices) {
		this.detalleExamenMultipleChoices = detalleExamenMultipleChoices;
	}

	public Set getRespuestas() {
		return this.respuestas;
	}

	public void setRespuestas(Set respuestas) {
		this.respuestas = respuestas;
	}

}