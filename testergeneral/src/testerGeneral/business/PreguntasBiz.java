package testerGeneral.business;

import java.util.List;

import testerGeneral.domain.DetalleExamenMultipleChoiceInterfaz;
import testerGeneral.domain.PersonaExamen;
import testerGeneral.domain.PreguntaInterfaz;
import testerGeneral.domain.RespuestaInterfaz;

public interface PreguntasBiz {

	public PreguntaInterfaz getPreguntaFromId(String idPregunta) throws Exception;	
	
	public List<RespuestaInterfaz> getRespuestasFromPregunta(PreguntaInterfaz preguntaInterfaz) throws Exception;
	
	public DetalleExamenMultipleChoiceInterfaz getDetalleExamenMCFromPersonaExamen(PersonaExamen personaExamen,PreguntaInterfaz pregunta) throws Exception;
	
	public void guardarDetalleExamen(PersonaExamen personaExamen,PreguntaInterfaz pregunta,RespuestaInterfaz respuestaSel) throws Exception;
	
	public RespuestaInterfaz getRespuestasFromPregunta(String idRespuesta) throws Exception;
	
	public boolean isExamenFinish(PersonaExamen personaExamen) throws Exception;
	
	public void finalizarExamen(PersonaExamen personaExamen,String resultadoMedico,String observaciones) throws Exception;
}
