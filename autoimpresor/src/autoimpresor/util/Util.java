package autoimpresor.util;

import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import testerGeneral.business.ContextManager;

public class Util {
	
	
	public static JasperReport compileReport() throws Exception
	{
		String margenIzqVal=ContextManager.getProperty("LIC_MARGEN_IZQ");
		String margenDerVal=ContextManager.getProperty("LIC_MARGEN_SUP");
		String desplazamientoTraseroVal=ContextManager.getProperty("LIC_DESPLAZAMIENTO_TRAS");
		
		String srcString = "reportes/carnetsSrc.jrxml";
		File srcFile = new File(srcString);
		String srcReport = srcFile.getParent() + File.separator
				+ "carnets.jrxml";


		FileInputStream fis = new FileInputStream(srcFile);

		int cant = fis.available();
		byte byt[] = new byte[cant];
		fis.read(byt);
		fis.close();
		String template = new String(byt);

		Double pixel = 1.3;

		Integer margenIzq = (int) (Integer.valueOf(margenIzqVal) * pixel);
		//Integer margenDer = (int) (Integer.valueOf(txtMargenDer.getText()) * pixel);
		Integer margenSup = (int) (Integer.valueOf(margenDerVal) * pixel);
		//Integer margenInf = (int) (Integer.valueOf(txtMargenInf.getText()) * pixel);

		int with = 237 + margenIzq;
		int colWith = with - margenIzq;
		int height = 156 + margenSup;

		template = template.replaceAll("#!MARGEN_IZQ!#", "" + margenIzq);
		//template = template.replaceAll("#!MARGEN_DER!#", "" + margenDer);
		template = template.replaceAll("#!MARGEN_TOP!#", "" + margenSup);
		//template = template.replaceAll("#!MARGEN_BOTTON!#", "" + margenInf);
		template = template.replaceAll("#!COLUMN_WITH!#", "" + colWith);
		template = template.replaceAll("#!PAGE_WITH!#", "" + with);
		template = template.replaceAll("#!PAGE_HEIGHT!#", "" + height);

		int desplazamientoTrasero=(int)(pixel*Integer.valueOf(desplazamientoTraseroVal));
		template = template.replaceAll("#!OBSERVACIONES_Y!#", (72+desplazamientoTrasero+""));
		template = template.replaceAll("#!RESTRICCIONES_Y!#", (40+desplazamientoTrasero+""));
		template = template.replaceAll("#!ALERGIAS_Y!#", (56+desplazamientoTrasero+""));
		template = template.replaceAll("#!GRUPO_SANGINEO_Y!#", (24+desplazamientoTrasero+""));
		template = template.replaceAll("#!TELEFONO_Y!#", (24+desplazamientoTrasero+""));
		template = template.replaceAll("#!DOMICILIO_Y!#", (8+desplazamientoTrasero+""));
		template = template.replaceAll("#!DONANTE_Y!#", (24+desplazamientoTrasero+""));
		template = template.replaceAll("#!MEDICACION_Y!#", (55+desplazamientoTrasero+""));
		template = template.replaceAll("#!CARGO_Y!#", (126+desplazamientoTrasero+""));
		template = template.replaceAll("#!NOMBRE_Y!#", (120+desplazamientoTrasero+""));
		template = template.replaceAll("#!FIRMA_Y!#", (92+desplazamientoTrasero+""));

		FileOutputStream fos = new FileOutputStream(srcReport);
		fos.write(template.getBytes());
		fos.close();
		
		JasperReport report = JasperCompileManager.compileReport(srcReport);
		
		return report;
	}
	
	public static void printReportCarnet(HashMap parameterMap,List list) {
		try {
			String srcString = "reportes/carnets.jasper";
			File f = new File(srcString);
			
			String printer=ContextManager.getProperty("DEFAULT_PRINTER");
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(list);
			JasperPrint jasperPrint = JasperFillManager.fillReport(f.getAbsolutePath(),parameterMap, ds);
			PrinterJob job = PrinterJob.getPrinterJob();

			PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
			for(int i = 0; i < services.length;i++){
				if(services[i].getName().equals(printer)){
					job.setPrintService(services[i]);
					break;
				}
			}
			
			JRPrintServiceExporter exporter;
			exporter = new JRPrintServiceExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);				
			exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE,job.getPrintService());
			exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, job.getPrintService().getAttributes());
			exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
			exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
			exporter.exportReport();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
