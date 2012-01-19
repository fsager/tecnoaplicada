package testerGeneral.domain;




public interface DetalleExamenMultipleChoiceInterfaz {

	public Long getId();

	public void setId(Long id);

	public PersonaExamen getPersonaExamen();

	public void setPersonaExamen(PersonaExamen personaExamen);

	public RespuestaInterfaz getRespuesta();

	public void setRespuesta(RespuestaInterfaz respuesta);
	
	public PreguntaInterfaz getPregunta();

	public void setPregunta(PreguntaInterfaz pregunta);

	public String getPreDetalle();

	public void setPreDetalle(String preDetalle);

	public String getResDetalle();

	public void setResDetalle(String resDetalle);

	public String getResCorrecta();

	public void setResCorrecta(String resCorrecta);

}
