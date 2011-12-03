package testerGeneral;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.sf.jasperreports.engine.JasperRunManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.GenericJDBCException;
import org.pushingpixels.substance.api.skin.SubstanceCremeLookAndFeel;

import testerGeneral.actualizaciones.GestorActualizaciones;
import testerGeneral.business.ContextManager;
import testerGeneral.db.ConexionManagerTesterGeneral;
import testerGeneral.domain.Auditoria;
import testerGeneral.domain.Constantes;
import testerGeneral.domain.PersonaExamen;
import testerGeneral.exceptions.MyExceptionHandler;
import testerGeneral.persistence.backup.GestorDBBackup;
import testerGeneral.service.AuditoriaDefinition;
import testerGeneral.service.PersonaExamenDefinition;
import testerGeneral.service.PersonaRestricionDefinition;
import testerGeneral.threads.ThreadTrama;
import ar.com.tecnologiaaplicada.domain.ExamenDetalle;
import ar.com.tecnologiaaplicada.service.ExamenDetalleDefinition;
import examenes.psicometrico.domain.TramaPsicologico;
import frontend.utils.Util;
import frontend.ventanas.FrameContenedor;
import frontend.ventanas.VentanaReportes;
import frontend.ventanas.VtnConfigurarDb;

public class Main {

	private static final Log log = LogFactory.getLog(Main.class);

	public static void main(String[] args)
			throws UnsupportedLookAndFeelException, InterruptedException,Exception {

		init();
		//probarLuces();
		//potenciometros();
		//imprimirResultado();
	}
	
	public static void imprimirResultado() throws Exception{
		JFrame frame=new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLayout(null);
		
		JPanel panel=new JPanel();
		panel.setBounds(0,0,800,600);
		frame.add(panel);
		
		frame.setVisible(true);
		
		
		PersonaExamenDefinition personaExamenService=(PersonaExamenDefinition)ContextManager.getBizObject("personaExamenService");
		PersonaRestricionDefinition personaRestriccionService=(PersonaRestricionDefinition)ContextManager.getBizObject("personaRestricionService");
		
		List<PersonaExamen> personaExamenes=personaExamenService.getAll(new PersonaExamen());
		int i=0;
		for(PersonaExamen personaExamen:personaExamenes)
		{
			i++;
			String otrasAflicciones=personaRestriccionService.getOtrasAflicciones(personaExamen.getPersona());
			
			System.out.println("personaExamen.getPexaId(): "+personaExamen.getPexaId()+", otrasAflicciones: "+otrasAflicciones+".");
			HashMap parameterMap = new HashMap();
			parameterMap.put("p_pexa_id", personaExamen.getPexaId());
			
			parameterMap.put("otrasAflicciones",otrasAflicciones);
			
			parameterMap.put("SUBREPORT_DIR",new File("./reportes").getCanonicalPath()+File.separator);	
			
			/*if(personaExamen.getPexaId().equals(7149) || personaExamen.getPexaId()==7149)
			{*/
				
				final byte[] buf = JasperRunManager.runReportToPdf(Constantes.RPT_PERSONA_EXAMEN, parameterMap, ContextManager.getCurrentConnection());
				
				String file=System.getProperty("java.io.tmpdir")+System.currentTimeMillis()+".pdf";
				testerGeneral.persistence.impl.Util.toFile(file,buf);
				
				Process p = Runtime.getRuntime().exec(
						"rundll32 url.dll,FileProtocolHandler "
								+ file);

			//}
			
			if(i==2000)
				break;			
		}
		
		/*PersonaExamen personaExamen=personaExamenService.get(new Long(7149));//7150  7149
		String otrasAflicciones=personaRestriccionService.getOtrasAflicciones(personaExamen.getPersona());
		
		System.out.println("personaExamen.getPexaId(): "+personaExamen.getPexaId()+", otrasAflicciones: "+otrasAflicciones+".");
		HashMap parameterMap = new HashMap();
		parameterMap.put("p_pexa_id", personaExamen.getPexaId());
		
		parameterMap.put("otrasAflicciones",otrasAflicciones);
		
		parameterMap.put("SUBREPORT_DIR",new File("./reportes").getCanonicalPath()+File.separator);	
		
			
			final byte[] buf = JasperRunManager.runReportToPdf(Constantes.RPT_PERSONA_EXAMEN, parameterMap, ContextManager.getCurrentConnection());
			
			String file=System.getProperty("java.io.tmpdir")+System.currentTimeMillis()+".pdf";
			testerGeneral.persistence.impl.Util.toFile(file,buf);
			
			Process p = Runtime.getRuntime().exec(
					"rundll32 url.dll,FileProtocolHandler "
							+ file);*/		
		

	}
	
	public static void getLicenciInformation() throws Exception
	{
		ExamenDetalleDefinition examenDetalleService=(ExamenDetalleDefinition)ContextManager.getBizObject("licenseRemoteService");		
		List<ExamenDetalle> examenDetalles = examenDetalleService.getDetalleLicenciaPorCliente("VBJ2-M107");

		for (int i = 0; i < examenDetalles.size(); i++) {
			ExamenDetalle detalle = examenDetalles.get(i);
			System.out.println("detalle: " + detalle.getExadCodigo());
		}		
	}

	public static void probarLuces() throws UnsupportedLookAndFeelException, InterruptedException,Exception
	{
		
		ThreadTrama thTrama = new ThreadTrama(new TramaPsicologico());
		Util.thTrama = thTrama;
		thTrama.setEjecucion(99999);
		thTrama.start();

		for(int i=0;i<200;i++)
		{
			//System.out.println("apagar: "+i);
			
			thTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_LED1);
			/*thTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_LED2);
			thTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_LED3);
			thTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_LED4);*/
			
			Thread.sleep(500);
		}
		
		for(int i=0;i<20;i++)
		{
			System.out.println("aslkdlaskdl: "+i);
			
			thTrama.sendOrden(ThreadTrama.ORDEN_PRENDER_LED1);
			Thread.sleep(350);
			thTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_LED1);
			Thread.sleep(350);
			
			thTrama.sendOrden(ThreadTrama.ORDEN_PRENDER_LED2);
			Thread.sleep(350);
			thTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_LED2);
			Thread.sleep(350);
			
			thTrama.sendOrden(ThreadTrama.ORDEN_PRENDER_LED3);
			Thread.sleep(350);
			thTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_LED3);
			Thread.sleep(350);
			
			thTrama.sendOrden(ThreadTrama.ORDEN_PRENDER_LED4);
			Thread.sleep(350);
			thTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_LED4);
			Thread.sleep(350);
			
			
			thTrama.sendOrden(ThreadTrama.ORDEN_PRENDER_LED1);
			thTrama.sendOrden(ThreadTrama.ORDEN_PRENDER_LED2);
			thTrama.sendOrden(ThreadTrama.ORDEN_PRENDER_LED3);
			thTrama.sendOrden(ThreadTrama.ORDEN_PRENDER_LED4);

			Thread.sleep(350);
			
			thTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_LED1);
			thTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_LED2);
			thTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_LED3);
			thTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_LED4);
			
			Thread.sleep(350);
		}
	}
	public static void potenciometros() {
		ThreadTrama thTrama = new ThreadTrama(new TramaPsicologico());
		thTrama.setEjecucion(99999);
		thTrama.start();

		while (true) {
			try {
				System.out.println("izq: "
						+ thTrama.getTramaValida().getPotenciometroIzquierdo()
						+ " der: "
						+ thTrama.getTramaValida().getPotenciometroDerecho());
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void init() {
		
		File archivoDeConexionDBRemota = new File(System.getProperty("user.dir")+ File.separator + "db_param.cfg");
		ConexionManagerTesterGeneral datasource = (ConexionManagerTesterGeneral) ContextManager.getBizObject("dataSource");
		
		try {
			try
			{
					datasource.getConnection();
					
					Util.SMALL_ICON = Constantes.IMG_ICON_SMALL;
					Util.obtenerNombrePC();
					AuditoriaDefinition auditoriaService = (AuditoriaDefinition) ContextManager
							.getBizObject("auditoriaService");
					Auditoria auditoria = new Auditoria();
					auditoria.setAudFecha(new Date());
					auditoriaService.getAll(auditoria);
		
					// Se borra el log anterior a los días especificados en el panel de
					// control.
					testerGeneral.persistence.impl.Util
							.borrarAuditoriasAnterioresAXDias();
		
					/*
					 * Si la actualización anterior incluía ejecutar un archivo de
					 * script (actualizar_db.sql), se actualiza con ese archivo la DB
					 * del programa.
					 */
					GestorDBBackup.ejecutarSentenciaSQL();
		
					try
					{
						/* Si se bajó una nueva versión del GestorActualizaciones.jar, se la
						 activa.*/
						//GestorActualizacionesUtil.actualizarGestorActualizaciones();
						
						/* Se revisa si hay una nueva versión del sistema al iniciar.*/
		
						GestorActualizaciones gestorActualizaciones = new GestorActualizaciones();
						gestorActualizaciones.start();
						
					}catch(Exception ex)
					{
						
					}
		
					// Hilo de backup automático al iniciar
					GestorDBBackup gestorBackup = new GestorDBBackup();
					Thread hiloBackupAutomatico = new Thread(gestorBackup);
					hiloBackupAutomatico.start();
		
					java.awt.EventQueue.invokeLater(new Runnable() {
						public void run() {
							ToolTipManager.sharedInstance().setInitialDelay(500);
							ToolTipManager.sharedInstance().setDismissDelay(15000);
							try {
								UIManager
										.setLookAndFeel(new SubstanceCremeLookAndFeel());
		
							} catch (Exception e) {
								throw new RuntimeException(e);
							}
							new FrameContenedor().setVisible(true);
							Thread
									.setDefaultUncaughtExceptionHandler(new MyExceptionHandler());
							System.setProperty("sun.awt.exception.handler",
									MyExceptionHandler.class.getName());
						}
					});

			} catch (Exception e) {
				
				if(archivoDeConexionDBRemota.exists())
					mostrarFrameJDBCRemoto();
				else
					throw new GenericJDBCException(null,null);
					
			}

		} catch (GenericJDBCException ex) {
			mostrarError("Se ha detectado que existe otra instancia de la aplicación que se está ejecutando. Por favor cierre dicha instancia antes de continuar.");
			log.error(ex.getMessage(), ex);
			System.exit(1);
		} catch (Exception exc) {
			throw new RuntimeException(exc);
		}
	}

	public static void mostrarError(final String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
	
	public static void mostrarFrameJDBCRemoto()
	{
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager
							.setLookAndFeel(new SubstanceCremeLookAndFeel());

				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				VtnConfigurarDb VtnConfigurarDb=new VtnConfigurarDb("/images/icon.png","TesterGeneral");
			}
		});
	}

}
