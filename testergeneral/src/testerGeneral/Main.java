package testerGeneral;

import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.GenericJDBCException;
import org.pushingpixels.substance.api.skin.SubstanceCremeLookAndFeel;

import actualizaciones.GestorActualizacionesUtil;

import testerGeneral.actualizaciones.GestorActualizaciones;
import testerGeneral.business.ContextManager;
import testerGeneral.domain.Auditoria;
import testerGeneral.domain.Constantes;
import testerGeneral.exceptions.MyExceptionHandler;
import testerGeneral.persistence.backup.GestorDBBackup;
import testerGeneral.seguridad.Encriptadora;
import testerGeneral.service.AuditoriaDefinition;
import testerGeneral.threads.ThreadTrama;
import examenes.psicometrico.domain.TramaPsicologico;
import examenes.psicometrico.domain.TramaVision;
import frontend.utils.Util;
import frontend.ventanas.FrameContenedor;

public class Main {

	private static final Log log = LogFactory.getLog(Main.class);

	public static void main(String[] args)
			throws UnsupportedLookAndFeelException, InterruptedException {
		init();
		//probarLuces();
		//potenciometros();
	}

	public static void probarLuces() throws UnsupportedLookAndFeelException, InterruptedException
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
		long iniTime = System.currentTimeMillis();
		try {
			Util.SMALL_ICON = Constantes.IMG_ICON_SMALL;

			Util.obtenerNombrePC();
			AuditoriaDefinition auditoriaService = (AuditoriaDefinition) ContextManager
					.getBizObject("auditoriaService");
			Auditoria auditoria = new Auditoria();
			auditoria.setAudFecha(new Date());
			auditoriaService.getAll(auditoria);

			// Se borra el log anterior a los d�as especificados en el panel de
			// control.
			testerGeneral.persistence.impl.Util
					.borrarAuditoriasAnterioresAXDias();

			/*
			 * Si la actualizaci�n anterior inclu�a ejecutar un archivo de
			 * script (actualizar_db.sql), se actualiza con ese archivo la DB
			 * del programa.
			 */
			GestorDBBackup.ejecutarSentenciaSQL();

			try
			{
				/* Si se baj� una nueva versi�n del GestorActualizaciones.jar, se la
				 activa.*/
				//GestorActualizacionesUtil.actualizarGestorActualizaciones();
				
				/* Se revisa si hay una nueva versi�n del sistema al iniciar.*/

				GestorActualizaciones gestorActualizaciones = new GestorActualizaciones();
				gestorActualizaciones.start();
				
			}catch(Exception ex)
			{
				
			}

			// Hilo de backup autom�tico al iniciar
			GestorDBBackup gestorBackup = new GestorDBBackup();
			Thread hiloBackupAutomatico = new Thread(gestorBackup);
			hiloBackupAutomatico.start();

			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
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

			System.out.println("1: " + (System.currentTimeMillis() - iniTime));

		} catch (GenericJDBCException ex) {
			mostrarError("Se ha detectado que existe otra instancia de la aplicaci�n que se est� ejecutando. Por favor cierre dicha instancia antes de continuar.");
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

}
