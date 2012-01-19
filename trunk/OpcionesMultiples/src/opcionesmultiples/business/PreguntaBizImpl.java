package opcionesmultiples.business;

import java.util.List;

import opcionesmultiples.domain.DetalleExamenMultipleChoice;
import opcionesmultiples.domain.Pregunta;
import opcionesmultiples.domain.Respuesta;
import opcionesmultiples.service.DetalleExamenMultipleChoiceDefinition;
import opcionesmultiples.service.PreguntaDefinition;
import opcionesmultiples.service.RespuestaDefinition;
import testerGeneral.business.ContextManager;
import testerGeneral.business.PreguntasBiz;
import testerGeneral.domain.DetalleExamenMultipleChoiceInterfaz;
import testerGeneral.domain.Examen;
import testerGeneral.domain.PersonaExamen;
import testerGeneral.domain.PreguntaInterfaz;
import testerGeneral.domain.RespuestaInterfaz;
import testerGeneral.service.PersonaExamenDefinition;

public class PreguntaBizImpl implements PreguntasBiz{
	DetalleExamenMultipleChoiceDefinition detalleExamenMultipleChoiceService = (DetalleExamenMultipleChoiceDefinition) ContextManager.getBizObject("detalleExamenMultipleChoiceService");
	PreguntaDefinition preguntaService = (PreguntaDefinition) ContextManager.getBizObject("preguntaService");
	RespuestaDefinition respuestaService = (RespuestaDefinition) ContextManager.getBizObject("respuestaService");
	PersonaExamenDefinition personaExamenService = (PersonaExamenDefinition) ContextManager.getBizObject("personaExamenService");


	public PreguntaInterfaz getPreguntaFromId(String idPregunta) throws Exception {
		
		Pregunta pregunta=preguntaService.get(Long.valueOf(idPregunta));
		
		return pregunta;
	}

	public DetalleExamenMultipleChoiceInterfaz getDetalleExamenMCFromPersonaExamen(PersonaExamen personaExamen,PreguntaInterfaz pregunta) throws Exception {
		
		DetalleExamenMultipleChoice example=new DetalleExamenMultipleChoice();
		example.setPersonaExamen(personaExamen);
		example.setPregunta(pregunta);
		
		List<DetalleExamenMultipleChoice> list=detalleExamenMultipleChoiceService.getAll(example);
		
		if(list.size()==1)
			return list.get(0);
		else if (list.size()>1)
			throw new RuntimeException("Respuestas duplicadas");
		
		return null;
	}
	
	public void guardarDetalleExamen(PersonaExamen personaExamen,PreguntaInterfaz pregunta,RespuestaInterfaz respuestaSel) throws Exception {
		
		DetalleExamenMultipleChoiceInterfaz detalleExamenMultipleChoice = getDetalleExamenMCFromPersonaExamen(personaExamen,pregunta);
		
		if(detalleExamenMultipleChoice==null)
			detalleExamenMultipleChoice=new DetalleExamenMultipleChoice();
		
		detalleExamenMultipleChoice.setPersonaExamen(personaExamen);
		detalleExamenMultipleChoice.setPregunta(pregunta);
		detalleExamenMultipleChoice.setRespuesta(respuestaSel);
		detalleExamenMultipleChoice.setPreDetalle(pregunta.getPreDetalle());
		detalleExamenMultipleChoice.setResDetalle(respuestaSel.getResDetalle());
		detalleExamenMultipleChoice.setResCorrecta(respuestaSel.getResCorrecta());
		
		if(detalleExamenMultipleChoice.getId()==null)
			detalleExamenMultipleChoiceService.insert((DetalleExamenMultipleChoice)detalleExamenMultipleChoice);
		else
			detalleExamenMultipleChoiceService.update((DetalleExamenMultipleChoice)detalleExamenMultipleChoice);
		
	}

	public List<RespuestaInterfaz> getRespuestasFromPregunta(PreguntaInterfaz preguntaInterfaz) throws Exception {
		Respuesta respuesta=new Respuesta();
		respuesta.setPregunta(preguntaInterfaz);
		
		return respuestaService.getAll(respuesta);
	}

	public RespuestaInterfaz getRespuestasFromPregunta(String idRespuesta) throws Exception {
		
		return respuestaService.get(Long.valueOf(idRespuesta));
	}

	public boolean isExamenFinish(PersonaExamen personaExamen) throws Exception {
		
		DetalleExamenMultipleChoice example=new DetalleExamenMultipleChoice();
		example.setPersonaExamen(personaExamen);
		
		List<DetalleExamenMultipleChoice> list=detalleExamenMultipleChoiceService.getAll(example);
		
		opcionesmultiples.domain.PersonaExamen personaExamenOM=(opcionesmultiples.domain.PersonaExamen)personaExamen;
		if(personaExamenOM.getPexaCantidadPreguntas().compareTo(list.size())>0)
		{
			return false;
		}		
		
		return true;
	}
	
	public void finalizarExamen(PersonaExamen personaExamen,String resultadoMedico,String observaciones) throws Exception {
		
		DetalleExamenMultipleChoice example=new DetalleExamenMultipleChoice();
		example.setPersonaExamen(personaExamen);
		
		List<DetalleExamenMultipleChoice> list=detalleExamenMultipleChoiceService.getAll(example);
		long totalExamen=0;
		long totalCorrecto=0;
		for(DetalleExamenMultipleChoice detalleExamenMultipleChoice:list)
		{
			
			long preguntaValoracion=detalleExamenMultipleChoice.getPregunta().getPreValoracion();
			totalExamen+=preguntaValoracion;
			if(detalleExamenMultipleChoice.getRespuesta().getResCorrecta().equals("S"))
			{
				totalCorrecto+=preguntaValoracion;
			}
		}
		
		opcionesmultiples.domain.PersonaExamen personaExamenOM=(opcionesmultiples.domain.PersonaExamen)personaExamen;
		personaExamenOM.setPexaEstado(Examen.ESTADO_FIN);
		personaExamenOM.setPexaResultadoMedico(resultadoMedico);
		personaExamenOM.setPexaObs(observaciones);
		double nota=(totalCorrecto*100)/(double)totalExamen;
		
		personaExamenOM.setPexaNota(nota);
		//personaExamenOM.setPexaTiempoUtilizado(pexaTiempoUtilizado)
		
		personaExamenService.update(personaExamenOM);
	}
}
