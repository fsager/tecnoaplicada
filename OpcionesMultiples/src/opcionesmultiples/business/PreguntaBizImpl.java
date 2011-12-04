package opcionesmultiples.business;

import opcionesmultiples.domain.Pregunta;
import opcionesmultiples.service.PreguntaDefinition;
import testerGeneral.business.ContextManager;
import testerGeneral.business.PreguntasBiz;
import testerGeneral.domain.PreguntaInterfaz;

public class PreguntaBizImpl implements PreguntasBiz{

	public PreguntaInterfaz getPreguntaFromId(String idPregunta) throws Exception {
		
		PreguntaDefinition preguntaService = (PreguntaDefinition) ContextManager.getBizObject("preguntaService");
		Pregunta pregunta=preguntaService.get(Long.valueOf(idPregunta));
		
		return pregunta;
	}

}
