package opcionesmultiples.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import testerGeneral.domain.Examen;
import testerGeneral.domain.Persona;

/**
 * PersonaExamen entity. @author MyEclipse Persistence Tools
 */

public class PersonaExamen extends testerGeneral.domain.PersonaExamen implements java.io.Serializable {

	private Integer pexaCantidadPreguntas;
	private Integer pexaTiempo;
	private Integer pexaTiempoUtilizado;
	public Integer getPexaCantidadPreguntas() {
		return pexaCantidadPreguntas;
	}
	public void setPexaCantidadPreguntas(Integer pexaCantidadPreguntas) {
		this.pexaCantidadPreguntas = pexaCantidadPreguntas;
	}
	public Integer getPexaTiempo() {
		return pexaTiempo;
	}
	public void setPexaTiempo(Integer pexaTiempo) {
		this.pexaTiempo = pexaTiempo;
	}
	public Integer getPexaTiempoUtilizado() {
		return pexaTiempoUtilizado;
	}
	public void setPexaTiempoUtilizado(Integer pexaTiempoUtilizado) {
		this.pexaTiempoUtilizado = pexaTiempoUtilizado;
	}	   
}