package testerGeneral.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import frontend.utils.Util;

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
	
	public boolean hasAmPersonaPermition()
	{
		return hasPermition("AM_PERSONA");
	}
	
	public boolean hasBPersonaPermition()
	{
		return hasPermition("B_PERSONA");
	}

	public boolean hasAccesoTareasAdmPermition()
	{
		return hasPermition("ACCESO_TAREAS_ADM");
	}
	
	public boolean hasAbmUsuarioPermition()
	{
		return hasPermition("ABM_USR");
	}

	
	private boolean hasPermition(String codigo)
	{
		Set<UsuarioPermiso> set=this.getUsuarioPermisos();
		for(UsuarioPermiso usrPer:set)
		{
			if(usrPer.getPermiso().getPerCodigo().equals(codigo))
				return true;
		}
		
		return false;
	}
	
	public boolean hasExamenPermition(String exaCodigo)
	{
		Set<UsuarioExamen> set=this.getUsuarioExamens();
		for(UsuarioExamen usrExa:set)
		{
			if(usrExa.getExamen().getExaCodigo().equals(exaCodigo))
				return true;
		}
		
		return false;
	}	
}