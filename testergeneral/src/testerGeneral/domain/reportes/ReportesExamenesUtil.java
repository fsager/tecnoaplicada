package testerGeneral.domain.reportes;

import java.util.HashMap;
import java.util.List;

import testerGeneral.domain.ExamenDetalle;

import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportesExamenesUtil{
	
	public static Integer getIntervalosInformesExamen(String exadCodigo)
	{
		if(exadCodigo.equals(ExamenDetalle.EXAD_CODIGO_TEST_COOR_BIMANUAL))
			return 8;
		else if(exadCodigo.equals(ExamenDetalle.EXAD_CODIGO_TEST_CTR_TEMPORO))
			return 9;
		else if(exadCodigo.equals(ExamenDetalle.EXAD_CODIGO_TEST_PERC_REAC))
			return 9;
		else if(exadCodigo.equals(ExamenDetalle.EXAD_CODIGO_TEST_REAC_MULTIPLES_COND))
			return 9;
		else if(exadCodigo.equals(ExamenDetalle.EXAD_CODIGO_TEST_REAC_SIMPLE))
			return 9;
		else if(exadCodigo.equals(ExamenDetalle.EXAD_CODIGO_TEST_COOR_BIMANUAL_FINA))			
			return 9;
		else if(exadCodigo.equals(ExamenDetalle.EXAD_CODIGO_TEST_REAC_MULT_NO_COND))
			return 9;
		else if(exadCodigo.equals(ExamenDetalle.EXAD_CODIGO_TEST_COOR_VISOMOTORA))
			return 9;
		
		throw new RuntimeException("getIntervalosInformesExamen: "+exadCodigo);
	}
	
	public static Double getTamañoIntervaloInformesExamen(String  exadCodigo)
	{
		if(exadCodigo.equals(ExamenDetalle.EXAD_CODIGO_TEST_COOR_BIMANUAL))
			return new Double(200);
		else if(exadCodigo.equals(ExamenDetalle.EXAD_CODIGO_TEST_CTR_TEMPORO))
			return new Double(2);
		else if(exadCodigo.equals(ExamenDetalle.EXAD_CODIGO_TEST_PERC_REAC))
			return new Double(10);
		else if(exadCodigo.equals(ExamenDetalle.EXAD_CODIGO_TEST_REAC_MULTIPLES_COND))
			return new Double(10);
		else if(exadCodigo.equals(ExamenDetalle.EXAD_CODIGO_TEST_REAC_SIMPLE))
			return new Double(3);
		else if(exadCodigo.equals(ExamenDetalle.EXAD_CODIGO_TEST_COOR_BIMANUAL_FINA))			
			return new Double(200);
		else if(exadCodigo.equals(ExamenDetalle.EXAD_CODIGO_TEST_REAC_MULT_NO_COND))
			return new Double(10);
		else if(exadCodigo.equals(ExamenDetalle.EXAD_CODIGO_TEST_COOR_VISOMOTORA))
			return new Double(180);
		
		throw new RuntimeException("getTamañoIntervaloInformesExamen: "+exadCodigo);
	}
	
	public static Double getOffsetInformesExamen(String  exadCodigo)
	{
		if(exadCodigo.equals(ExamenDetalle.EXAD_CODIGO_TEST_COOR_BIMANUAL))
			return new Double(0);
		else if(exadCodigo.equals(ExamenDetalle.EXAD_CODIGO_TEST_CTR_TEMPORO))
			return new Double(0);
		else if(exadCodigo.equals(ExamenDetalle.EXAD_CODIGO_TEST_PERC_REAC))
			return new Double(40);
		else if(exadCodigo.equals(ExamenDetalle.EXAD_CODIGO_TEST_REAC_MULTIPLES_COND))
			return new Double(40);
		else if(exadCodigo.equals(ExamenDetalle.EXAD_CODIGO_TEST_REAC_SIMPLE))
			return new Double(20);
		else if(exadCodigo.equals(ExamenDetalle.EXAD_CODIGO_TEST_COOR_BIMANUAL_FINA))			
			return new Double(0);
		else if(exadCodigo.equals(ExamenDetalle.EXAD_CODIGO_TEST_REAC_MULT_NO_COND))
			return new Double(40);
		else if(exadCodigo.equals(ExamenDetalle.EXAD_CODIGO_TEST_COOR_VISOMOTORA))
			return new Double(0);
		
		throw new RuntimeException("getTamañoIntervaloInformesExamen: "+exadCodigo);
	}
	
	public static String[] getCategorias(String exadCodigo)
	{
		int intervalos=getIntervalosInformesExamen(exadCodigo);
		double tamañoIntervalo=getTamañoIntervaloInformesExamen(exadCodigo);
		double offset=getOffsetInformesExamen(exadCodigo);
		String[] categorias=new String[intervalos+1];

		for(int intervaloActual=0;intervaloActual<categorias.length-1;intervaloActual++)
		{
			double valorActual=(intervaloActual*tamañoIntervalo);			
			double valorSiguiente=valorActual+tamañoIntervalo+offset;
			//categorias[intervaloActual]=new String(valorActual+ " - "+valorSiguiente);
			categorias[intervaloActual]=new String(""+valorSiguiente);
			
			/*if(exadCodigo.equals(ExamenDetalle.EXAD_CODIGO_TEST_REAC_SIMPLE))
			{
			
				System.out.println("intervalos: "+intervalos+" tamañoIntervalo: "+tamañoIntervalo+" offset: "+offset+" valorSiguiente:"+valorSiguiente);
			}*/
		}
		
		categorias[categorias.length-1]=new String("Más de "+(((categorias.length-1)*tamañoIntervalo)+offset));

		return categorias;
	}
	
	public static String getCategoria(String exadCodigo,double valor)
	{
		String[] categorias=getCategorias(exadCodigo);
		int intervalos=getIntervalosInformesExamen(exadCodigo);
		double offset=getOffsetInformesExamen(exadCodigo);
		double tamañoIntervalo=getTamañoIntervaloInformesExamen(exadCodigo);
		for(int intervaloActual=0;intervaloActual<categorias.length-1;intervaloActual++)
		{
			double valorActual=(intervaloActual*tamañoIntervalo);
			double valorSiguiente=valorActual+tamañoIntervalo+offset;

			if(valor>=valorActual && valor<valorSiguiente)
			{
				return categorias[intervaloActual];
			}
		}
		return categorias[categorias.length-1];
	}
	
	public static String[] getCategoriasErrores()
	{
		int intervalos=9;
		double tamañoIntervalo=3;
		String[] categorias=new String[intervalos+1];
		
		for(int intervaloActual=0;intervaloActual<categorias.length-1;intervaloActual++)
		{
			double valorActual=(intervaloActual*tamañoIntervalo);
			double valorSiguiente=valorActual+tamañoIntervalo;
			//categorias[intervaloActual]=new String(valorActual+ " - "+valorSiguiente);
			categorias[intervaloActual]=new String(""+valorSiguiente);
		}
		
		categorias[categorias.length-1]=new String("Más de "+((categorias.length-1)*tamañoIntervalo));

		return categorias;
	}
	
	public static String getCategoriaErrores(Double valor)
	{
		String[] categorias=getCategoriasErrores();
		double tamañoIntervalo=3;
		for(int intervaloActual=0;intervaloActual<categorias.length-1;intervaloActual++)
		{
			double valorActual=(intervaloActual*tamañoIntervalo);
			double valorSiguiente=valorActual+tamañoIntervalo;

			if(valor>=valorActual && valor<valorSiguiente)
			{
				return categorias[intervaloActual];
			}
		}
		return categorias[categorias.length-1];
	}
	
	public static void main(String args[]) throws Exception
	{
		/*ReportesExamenesUtil reporteCoordinacionBimanual=new ReportesExamenesUtil();
		reporteCoordinacionBimanual.getCategorias();
		System.out.println("0: "+reporteCoordinacionBimanual.getCategoria(new Double(0)));
		System.out.println("1: "+reporteCoordinacionBimanual.getCategoria(new Double(1)));
		System.out.println("3599: "+reporteCoordinacionBimanual.getCategoria(new Double(3599)));
		System.out.println("3600: "+reporteCoordinacionBimanual.getCategoria(new Double(3600)));
		System.out.println("3601: "+reporteCoordinacionBimanual.getCategoria(new Double(3601)));
		System.out.println("5000: "+reporteCoordinacionBimanual.getCategoria(new Double(5000)));
		
		System.out.println("500: "+reporteCoordinacionBimanual.getCategoria(new Double(500)));
		System.out.println("322: "+reporteCoordinacionBimanual.getCategoria(new Double(322)));
		System.out.println("859: "+reporteCoordinacionBimanual.getCategoria(new Double(859)));
		System.out.println("1200: "+reporteCoordinacionBimanual.getCategoria(new Double(1200)));
		System.out.println("3212: "+reporteCoordinacionBimanual.getCategoria(new Double(3212)));*/
		
	}

}
