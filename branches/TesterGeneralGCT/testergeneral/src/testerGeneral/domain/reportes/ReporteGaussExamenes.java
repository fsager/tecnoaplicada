//testerGeneral.domain.reportes.ReporteGaussExamenes
package testerGeneral.domain.reportes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import testerGeneral.domain.ExamenDetalle;



public class ReporteGaussExamenes {
	private String serie;//Nombre del Examen
	private String categoria;//01,2,3,etc para errores. 1-3, 3-5, etc para tiempo o metros
	private int value;//Cantidad de personas
	private String tipo;//C CATEGORIA - E error
	
	public static void addExamen(List<ReporteGaussExamenes> reportesGaussExamenes,ExamenDetalle examenDetalle)
	{
		String categorias[]=ReportesExamenesUtil.getCategorias(examenDetalle.getExadCodigo());
		for(String categoria:categorias)
		{
			ReporteGaussExamenes reporteGaussExamenes=new ReporteGaussExamenes();
			reporteGaussExamenes.setCategoria(categoria);
			reporteGaussExamenes.setSerie(examenDetalle.getExadDetalle());
			reporteGaussExamenes.setValue(0);
			reporteGaussExamenes.setTipo("C");
			
			reportesGaussExamenes.add(reporteGaussExamenes);
		}
		
		categorias=ReportesExamenesUtil.getCategoriasErrores();
		for(String categoria:categorias)
		{
			
			ReporteGaussExamenes reporteGaussExamenes=new ReporteGaussExamenes();
			reporteGaussExamenes.setCategoria(categoria);
			reporteGaussExamenes.setSerie(examenDetalle.getExadDetalle());
			reporteGaussExamenes.setValue(0);
			reporteGaussExamenes.setTipo("E");
			
			reportesGaussExamenes.add(reporteGaussExamenes);
		}
	}
	
	public void addValue()
	{
		value++;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getSerie() {
		return serie;
	}
	public void setSerie(String seria) {
		this.serie = seria;
	}
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	public static List<ReporteGaussExamenes> getReporteGaussExamenes()
	{
		List<ReporteGaussExamenes> lstReporte=new ArrayList<ReporteGaussExamenes>(); 
		
		ReporteGaussExamenes r=new ReporteGaussExamenes();
		r.setSerie("Test de control temporo espacial");
		r.setCategoria("TOP 1");
		r.setValue(1);
			
		lstReporte.add(r);
			
		r=new ReporteGaussExamenes();
		r.setSerie("Test de control temporo espacial");
		r.setCategoria("TOP 2");
		r.setValue(5);
			
		lstReporte.add(r);
			
		r=new ReporteGaussExamenes();
		r.setSerie("Test de control temporo espacial");
		r.setCategoria("TOP 3");
		r.setValue(2);
			
		lstReporte.add(r);
		
			
		
		/*r=new ReporteGaussExamenes();
		r.setSerie("Examen 2");
		r.setCategoria("TOP 1");
		r.setValue(1);
		
		List<CategoriaValor> catLst=new ArrayList<CategoriaValor>();
		
		CategoriaValor cat=new CategoriaValor();
		cat.setCategoria("TOP 1");
		cat.setValue(2);
		catLst.add(cat);
		
		cat=new CategoriaValor();
		cat.setCategoria("TOP 2");
		cat.setValue(7);
		catLst.add(cat);
		
		cat=new CategoriaValor();
		cat.setCategoria("TOP 3");
		cat.setValue(3);
		catLst.add(cat);
		
		r.setCategoriasValores(catLst);
		lstReporte.add(r);*/
			
			
		return lstReporte;
	}
	
	
	public static void main(String args[]) throws Exception
	{
		HashMap parameterMap = new HashMap();
		List<ReporteGaussExamenes> list=getReporteGaussExamenes();
		JRBeanCollectionDataSource ds =new JRBeanCollectionDataSource(list);
		final byte[] buf = JasperRunManager.runReportToPdf("reportes/estadisticaExamenes.jasper", parameterMap,ds);
		
		String file=System.getProperty("java.io.tmpdir")+System.currentTimeMillis()+".pdf";
		testerGeneral.persistence.impl.Util.toFile(file,buf);
		
		Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+ file);
	}
}
