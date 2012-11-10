package testerGeneral.domain;


public interface RespuestaInterfaz {

	public Long getId();

	public void setId(Long id);

	public PreguntaInterfaz getPregunta();

	public void setPregunta(PreguntaInterfaz pregunta);	

	public String getResDetalle();

	public void setResDetalle(String resDetalle);

	public String getResCorrecta();

	public void setResCorrecta(String resCorrecta);	
}
