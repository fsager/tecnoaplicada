package tecnologia.aplicada.licence.autoimpresor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import autoimpresor.domain.Licencia;
import autoimpresor.domain.Persona;
import autoimpresor.service.LicenciaDefinition;
import frontend.utils.Util;

import tecnologia.aplicada.licence.CodigoActivacionDefinition;
import testerGeneral.business.ContextManager;
import testerGeneral.domain.Constantes;
import testerGeneral.service.PersonaExamenDefinition;

public class CodigoActivacionImp implements CodigoActivacionDefinition{

	public String getCodigoActivacionInfo(Date dateActualizacion) throws Exception{

		LicenciaDefinition licenciaService = (LicenciaDefinition) ContextManager.getBizObject("licenciaService");

		int cantidad=licenciaService.getAll(new Licencia(),dateActualizacion,null).size();
		return String.valueOf(cantidad);
	}

}