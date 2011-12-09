package tecnologia.aplicada.licence;

import java.util.Date;

import testerGeneral.business.ContextManager;
import testerGeneral.service.PersonaExamenDefinition;

public class CodigoActivacionImp implements CodigoActivacionDefinition{

	public String getCodigoActivacionInfo(Date dateActualizacion) throws Exception{
		PersonaExamenDefinition personaExamenService = (PersonaExamenDefinition) ContextManager.getBizObject("personaExamenService");
		Long cantidad = personaExamenService.getCantidadExamenes(dateActualizacion);
		
		return cantidad.toString();
	}

}
