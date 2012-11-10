package examenes.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.Examen;
import testerGeneral.domain.ExamenDetalle;
import testerGeneral.domain.PersonaExamen;
import testerGeneral.domain.PreguntaInterfaz;
import testerGeneral.domain.Resultado;
import testerGeneral.domain.Usuario;
import testerGeneral.service.ExamenDefinition;
import frontend.paneles.examenes.PanelDetalleExamen;
import frontend.paneles.examenes.PanelExamen;
import frontend.paneles.examenes.PanelExamenes;
import frontend.paneles.psicometrico.anticipacion.PanelAnticipacion;
import frontend.paneles.vision.PanelAgudezaVisual;
import frontend.utils.Util;

public class ExamenesUtils {
	
	private static final Log log = LogFactory.getLog(ExamenesUtils.class);
	
	public static Double[] calcularPromedio(List<Resultado> resultados) {
		return calcularPromedio(toArray(resultados));
	}
	
	public static Double[] calcularPromedio(Set<Resultado> resultados) {		
		return calcularPromedio(toArray(resultados));
	}
	
	public static Double[] calcularPromedio(Resultado[] resultados) {
		log.debug("ini calcularPromedio");
		
		int cant = 0;
		Double[] prom = new Double[2];

		if (resultados.length > 0) {
			for (int i = 0; i < resultados.length; i++) {
				if (resultados[i].getResValor1() != null) {
					prom[0] = new Double(0);
				}
				if (resultados[i].getResValor2() != null) {
					prom[1] = new Double(0);
				}
			}
		}

		for (int i = 0; i < resultados.length; i++) {
			if (prom[0] != null && resultados[i].getResValor1() != null && (resultados[i].getResValor2() == null || resultados[i].getResValor2() == 0)) {
				prom[0] += Math.abs(resultados[i].getResValor1());
				cant++;
			}
			if (prom[1] != null && resultados[i].getResValor2() != null) {
				prom[1] += Math.abs(resultados[i].getResValor2());
			}
		}

		if (prom[0] != null && cant!=0) {
			prom[0] = prom[0] / (double)cant;
			prom[0] = Util.redondearTiempo(prom[0]);
		}
		else if(cant==0)
			prom[0]=0.0;

		log.debug("fin calcularPromedio");
		
		return prom;
	}
	
	public static String mostrarResultados(List<Resultado> resultados,ExamenDetalle exad)
	{
		log.debug("ini mostrarResultados");
		
		Double[] prom = calcularPromedio(resultados);
		String[] unidades=getUnidadesExamen(exad);
		
		if(exad.getExadCodigo().equals(ExamenDetalle.EXAD_CODIGO_TEST_COOR_BIMANUAL))
			prom[0]=resultados.get(0).getResValor1()+resultados.get(1).getResValor1();
		
		String res = "Resultado";
		
		if(prom[0]!=null)
				res+=" Promedio: "+prom[0]+" "+unidades[0];
		
		if(prom[1]!=null)
			res+=" "+unidades[1]+": "+prom[1];
		
		log.debug("fin mostrarResultados");
		return res;
	}
		
	public static String[] getUnidadesExamen(ExamenDetalle exad)
	{
		log.debug("ini getUnidadesExamen");
		
		if(exad.getExadCodigo().equals(ExamenDetalle.EXAD_CODIGO_TEST_CTR_TEMPORO))
		{
			String[] unidades={"Metros"};
			
			return unidades;
		}
		else if(exad.getExadCodigo().equals(ExamenDetalle.EXAD_CODIGO_TEST_PERC_REAC))
		{
			String[] unidades={"Centésimas de segundos","Errores"};
			
			return unidades;
		}
		else if(exad.getExadCodigo().equals(ExamenDetalle.EXAD_CODIGO_TEST_REAC_MULTIPLES_COND))
		{
			String[] unidades={"Centésimas de segundos","Errores"};
			
			return unidades;
		}
		else if(exad.getExadCodigo().equals(ExamenDetalle.EXAD_CODIGO_TEST_REAC_SIMPLE))
		{
			String[] unidades={"Centésimas de segundos","Errores"};
			
			return unidades;
		}
		else if(exad.getExadCodigo().equals(ExamenDetalle.EXAD_CODIGO_TEST_COOR_BIMANUAL_FINA))
		{
			String[] unidades={"Centésimas de segundos","Errores"};
			
			return unidades;
		}
		else if(exad.getExadCodigo().equals(ExamenDetalle.EXAD_CODIGO_TEST_REAC_MULT_NO_COND))
		{
			String[] unidades={"Centésima de segundos","Errores"};
			
			return unidades;
		}
		else if(exad.getExadCodigo().equals(ExamenDetalle.EXAD_CODIGO_TEST_COOR_VISOMOTORA))
		{
			String[] unidades={"Centésima de segundos","Errores"};
			
			return unidades;
		}
		else if(exad.getExadCodigo().equals(ExamenDetalle.EXAD_CODIGO_TEST_COOR_BIMANUAL))
		{
			String[] unidades={"Centésima de segundos","Errores"};
			
			return unidades;
		}
		
		log.debug("fin getUnidadesExamen");
		return null;
	}
	
	public static String detalleExamenResultado(ExamenDetalle exad,Resultado[] resultados)
	{
		log.debug("ini detalleExamenResultado");
		
		Double[] prom = calcularPromedio(resultados);
		if(exad.getExadCodigo().equals(ExamenDetalle.EXAD_CODIGO_TEST_CTR_TEMPORO))
		{
			Double metros=Double.valueOf(ContextManager.getProperty("EXAMEN.ANTICIPACION.METROS.PERMITIDOS.HASTA"));
			if(prom[0]<=metros)
				return Examen.RESULTADO_DENTRO;
			else
				return Examen.RESULTADO_FUERA;
		}
		else if(exad.getExadCodigo().equals(ExamenDetalle.EXAD_CODIGO_TEST_PERC_REAC))
		{
			Double errores=Double.valueOf(ContextManager.getProperty("EXAMEN.PERCEPCION.REACCION.ERRORES.PERMITIDOS.HASTA"));
			
			if(prom[1]<=errores)
				return Examen.RESULTADO_DENTRO;
			else
				return Examen.RESULTADO_FUERA;
		}
		else if(exad.getExadCodigo().equals(ExamenDetalle.EXAD_CODIGO_TEST_REAC_SIMPLE))
		{
			Double errores=Double.valueOf(ContextManager.getProperty("EXAMEN.REACCION.SIMPLE.ERRORES.PERMITIDOS.HASTA"));
			Double tiempo=(Double.valueOf(ContextManager.getProperty("EXAMEN.REACCION.SIMPLE.TIEMPO.PERMITIDOS.HASTA"))/10d);
			
			if(prom[0]<=tiempo && prom[1]<errores)
				return Examen.RESULTADO_DENTRO;
			else
				return Examen.RESULTADO_FUERA;
		}
		else if(exad.getExadCodigo().equals(ExamenDetalle.EXAD_CODIGO_TEST_REAC_MULTIPLES_COND))
		{
			Double errores=Double.valueOf(ContextManager.getProperty("EXAMEN.REACION.MULTIPLE.COND.ERRORES.PERMITIDOS.HASTA"));
			Double tiempo=Double.valueOf(ContextManager.getProperty("EXAMEN.REACION.MULTIPLE.COND.TIEMPO.PERMITIDOS.HASTA"));
			
			if(prom[0]<=tiempo && prom[1]<=errores)
				return Examen.RESULTADO_DENTRO;
			else
				return Examen.RESULTADO_FUERA;
		}
		else if(exad.getExadCodigo().equals(ExamenDetalle.EXAD_CODIGO_TEST_REAC_MULT_NO_COND))
		{
			Double errores=Double.valueOf(ContextManager.getProperty("EXAMEN.REACION.MULTIPLE.NOCOND.ERRORES.PERMITIDOS.HASTA"));
			Double tiempo=Double.valueOf(ContextManager.getProperty("EXAMEN.REACION.MULTIPLE.NOCOND.TIEMPO.PERMITIDOS.HASTA"));
			
			if(prom[0]<=tiempo && prom[1]<=errores)
				return Examen.RESULTADO_DENTRO;
			else
				return Examen.RESULTADO_FUERA;
		}
		else if(exad.getExadCodigo().equals(ExamenDetalle.EXAD_CODIGO_TEST_COOR_BIMANUAL))
		{
			Double tiempo=Double.valueOf(ContextManager.getProperty("EXAMEN.COORDINACION.BIMANUAL.TIEMPO.FUERA.PERMITIDO"));
			Double errores=Double.valueOf(ContextManager.getProperty("EXAMEN.COORDINACION.BIMANUAL.ERRORES.PERMITIDO"));
			prom[0]=resultados[0].getResValor1()+resultados[1].getResValor1();
			
			if(prom[0]<=tiempo && prom[1]<=errores)
				return Examen.RESULTADO_DENTRO;
			else
				return Examen.RESULTADO_FUERA;
		}
		
		log.debug("fin detalleExamenResultado");
		
		return null;
	}
	
	public static String detalleExamenResultado(ExamenDetalle exad,List<Resultado> resultados)
	{
		return detalleExamenResultado(exad,toArray(resultados));
	}
	
	public static String detalleExamenResultado(ExamenDetalle exad,Set<Resultado> resultados)
	{
		return detalleExamenResultado(exad,toArray(resultados));
	}
	
	public static Resultado[] toArray(List<Resultado> resultados)
	{
		log.debug("ini toArray");
		
		Resultado[] resultadosArray=new Resultado[resultados.size()];
		
		for(int i=0;i<resultados.size();i++)
		{
			resultadosArray[i]=resultados.get(i);
		}
		
		log.debug("fin toArray");
		
		return resultadosArray;
	}
	
	public static Resultado[] toArray(Set<Resultado> resultados)
	{
		log.debug("ini toArray");
		
		Resultado[] resultadosArray=new Resultado[resultados.size()];
		
		Iterator it=resultados.iterator();
		
		int i=0;
		while(it.hasNext())
		{
			resultadosArray[i]=(Resultado)it.next();
			i++;            
		}
		
		log.debug("fin toArray");
		
		return resultadosArray;
	}
	
	public static List<Examen> obtenerExamenes(boolean conLicencia,boolean conPermiso) throws Exception
	{
		log.debug("ini obtenerExamenes");
		
		Usuario usr=((Usuario)Util.usuarioCommon);
		List<Examen> examenes=new ArrayList<Examen>();
		ExamenDefinition examenService = (ExamenDefinition) ContextManager.getBizObject("examenService");
		examenes = examenService.getAll(new Examen());
		
		for(int i=examenes.size()-1;i>=0;i--)
		{
			Examen examen=examenes.get(i);
			boolean usrHasNotExamenPermition=!(usr.hasExamenPermition(examen.getExaCodigo()));
			if(!conPermiso)
				usrHasNotExamenPermition=false;
			
			Set<ExamenDetalle> examenDetalles=examen.getExamenDetalles();
			boolean hasLicence=false;
			if(conLicencia)
			{
				for(ExamenDetalle examenDetalle:examenDetalles)
				{
					hasLicence=examenDetalle.getExadLicencedSn().equals("S");
					if(hasLicence)
						break;
				}				
			}
			else
				hasLicence=true;
				
			
			if(!hasLicence || usrHasNotExamenPermition)
				examenes.remove(i);
		}
		
		
		/*if(conLicencia)
		{
			//throw new Exception("No esta implementada la validacion de la licencia");	
		}	*/	
		
		log.debug("fin obtenerExamenes");
		
		return examenes;
	}
	
	public static void mostrarPanelExamen(PersonaExamen personaExamen,JPanel panelContenido)
	{
		log.debug("ini mostrarPanelExamen");
		try
		{
			PersonaExamen newPersonaExamen=new PersonaExamen();
			
			newPersonaExamen.setPexaTipoExamen(personaExamen.getPexaTipoExamen());
			newPersonaExamen.setPersona(personaExamen.getPersona());
			newPersonaExamen.setExamen(personaExamen.getExamen());	
			
			panelContenido.removeAll();
			
			PanelExamenes panelExamen = null;
			if(newPersonaExamen.getExamen().getExaCodigo().equals(Examen.EXA_CODIGO_PSICOMETRICO))
				panelExamen = new PanelExamenes(newPersonaExamen,new PanelAnticipacion(null,newPersonaExamen));
			else if(newPersonaExamen.getExamen().getExaCodigo().equals(Examen.EXA_CODIGO_VISION))
				panelExamen = new PanelExamenes(newPersonaExamen,new PanelAgudezaVisual(null,newPersonaExamen));

			panelContenido.add(panelExamen);
			
			if(panelExamen.getPanelAnimacion().getComponentCount()>0)
				panelExamen.getPanelAnimacion().getComponent(0).requestFocus();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		log.debug("fin mostrarPanelExamen");
	}
	
	
	public static void mostrarPanelExamen(PersonaExamen personaExamen,JPanel panelContenido,PanelExamen panelExamenMultipleChoise,List<PreguntaInterfaz> preguntas) 
	{
		log.debug("ini mostrarPanelExamen");
		try
		{
			/*PersonaExamen newPersonaExamen=new PersonaExamen();
			
			newPersonaExamen.setPexaTipoExamen(personaExamen.getPexaTipoExamen());
			newPersonaExamen.setPersona(personaExamen.getPersona());
			newPersonaExamen.setExamen(personaExamen.getExamen());*/	
			
			panelContenido.removeAll();
			
			PanelExamenes panelExamen=new PanelExamenes(personaExamen,panelExamenMultipleChoise,preguntas);
			panelContenido.add(panelExamen);
			
			PanelDetalleExamen panelDetalleExamen=panelExamen.getPanelDetalleExamen();
			panelDetalleExamen.seleccionarPregunta(panelDetalleExamen.getBtnExamenPrimero());
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		log.debug("fin mostrarPanelExamen");
	}

}
